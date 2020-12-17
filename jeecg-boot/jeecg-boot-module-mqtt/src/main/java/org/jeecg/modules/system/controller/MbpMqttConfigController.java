package org.jeecg.modules.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.system.mqtt.MqttCallBackUtil;
import org.jeecg.modules.system.mqtt.PublishSample;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.MbpMqttTopics;
import org.jeecg.modules.system.entity.MbpMqttConfig;
import org.jeecg.modules.system.vo.MbpMqttConfigPage;
import org.jeecg.modules.system.service.IMbpMqttConfigService;
import org.jeecg.modules.system.service.IMbpMqttTopicsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: MQTT配置信息
 * @Author: jeecg-boot
 * @Date:   2020-12-01
 * @Version: V1.0
 */
@Api(tags="MQTT配置信息")
@RestController
@RequestMapping("/system/mbpMqttConfig")
@Slf4j
public class MbpMqttConfigController {
	@Autowired
	private IMbpMqttConfigService mbpMqttConfigService;
	@Autowired
	private IMbpMqttTopicsService mbpMqttTopicsService;
	@Autowired
	private RedisUtil redisUtil;

	 /**
	  * 开启订阅
	  *
	  * @param mbpMqttConfig
	  * @return
	  */
	 @AutoLog(value = "MQTT配置信息-开启订阅")
	 @ApiOperation(value="MQTT配置信息-开启订阅", notes="MQTT配置信息-开启订阅")
	 @GetMapping(value = "/startSub")
	 public Result<?> startSub(MbpMqttConfig mbpMqttConfig) throws InterruptedException {
		 MbpMqttConfig byId = mbpMqttConfigService.getById(mbpMqttConfig.getId());
		 if (byId.getDelFlag() == 1) {
			 return Result.error("客户端在线！请勿重复操作！");
		 }
		 QueryWrapper<MbpMqttTopics> queryWapper = new QueryWrapper<>();
		 queryWapper.eq("mqtt_config_id", byId.getId());
		 List<MbpMqttTopics> list = mbpMqttTopicsService.list(queryWapper);
		 List<String> topicList = list.stream().map(MbpMqttTopics::getTopic).collect(Collectors.toList());
		 String[] topics = new String[topicList.size()];
		 Stream.iterate(0, i -> i + 1).limit(topicList.size()).forEach(i -> {
			 topics[i] = topicList.get(i);
		 });
		 byId.setTopics(topics);

		 try {
			 MqttCallBackUtil util = new MqttCallBackUtil();
			 util.run(byId);
		 } catch (Exception e) {
			 log.info("MQTT连接异常：" + e);
		 	return Result.error("连接失败");
		 }

		 return Result.OK("连接成功！");
	 }


	 /**
	  * 取消订阅
	  *
	  * @param mbpMqttConfig
	  * @return
	  */
	 @AutoLog(value = "MQTT配置信息-取消订阅")
	 @ApiOperation(value="MQTT配置信息-取消订阅", notes="MQTT配置信息-取消订阅")
	 @GetMapping(value = "/stopSub")
	 public Result<?> stopSub(MbpMqttConfig mbpMqttConfig) throws InterruptedException {
		 MbpMqttConfig byId = mbpMqttConfigService.getById(mbpMqttConfig.getId());
		 QueryWrapper<MbpMqttTopics> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("mqtt_config_id",byId.getId());
		 List<MbpMqttTopics> list = mbpMqttTopicsService.list(queryWrapper);
		 PublishSample publishSample = new PublishSample();
		 try {
			 publishSample.destroyMqttClient(mbpMqttConfig, list.get(0).getTopic());
		 } catch (MqttException e) {
		 	 log.info("关闭订阅时异常：" + e );
			 return Result.error("操作失败");
		 }
		 MbpMqttConfig config = new MbpMqttConfig();
		 config.setId(mbpMqttConfig.getId());
		 config.setDelFlag(0);
		 mbpMqttConfigService.updateById(config);
		 log.info("客户端【" + byId.getMqttClientId() +"】取消订阅");
		 return Result.OK("操作成功");
	 }
	
	/**
	 * 分页列表查询
	 *
	 * @param mbpMqttConfig
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "MQTT配置信息-分页列表查询")
	@ApiOperation(value="MQTT配置信息-分页列表查询", notes="MQTT配置信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MbpMqttConfig mbpMqttConfig,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MbpMqttConfig> queryWrapper = QueryGenerator.initQueryWrapper(mbpMqttConfig, req.getParameterMap());
		Page<MbpMqttConfig> page = new Page<MbpMqttConfig>(pageNo, pageSize);
		IPage<MbpMqttConfig> pageList = mbpMqttConfigService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mbpMqttConfigPage
	 * @return
	 */
	@AutoLog(value = "MQTT配置信息-添加")
	@ApiOperation(value="MQTT配置信息-添加", notes="MQTT配置信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MbpMqttConfigPage mbpMqttConfigPage) {
		MbpMqttConfig mbpMqttConfig = new MbpMqttConfig();
		BeanUtils.copyProperties(mbpMqttConfigPage, mbpMqttConfig);
		mbpMqttConfigService.saveMain(mbpMqttConfig, mbpMqttConfigPage.getMbpMqttTopicsList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mbpMqttConfigPage
	 * @return
	 */
	@AutoLog(value = "MQTT配置信息-编辑")
	@ApiOperation(value="MQTT配置信息-编辑", notes="MQTT配置信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MbpMqttConfigPage mbpMqttConfigPage) {
		MbpMqttConfig mbpMqttConfig = new MbpMqttConfig();
		BeanUtils.copyProperties(mbpMqttConfigPage, mbpMqttConfig);
		MbpMqttConfig mbpMqttConfigEntity = mbpMqttConfigService.getById(mbpMqttConfig.getId());
		if(mbpMqttConfigEntity==null) {
			return Result.error("未找到对应数据");
		}
		mbpMqttConfigService.updateMain(mbpMqttConfig, mbpMqttConfigPage.getMbpMqttTopicsList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "MQTT配置信息-通过id删除")
	@ApiOperation(value="MQTT配置信息-通过id删除", notes="MQTT配置信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mbpMqttConfigService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "MQTT配置信息-批量删除")
	@ApiOperation(value="MQTT配置信息-批量删除", notes="MQTT配置信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mbpMqttConfigService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "MQTT配置信息-通过id查询")
	@ApiOperation(value="MQTT配置信息-通过id查询", notes="MQTT配置信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MbpMqttConfig mbpMqttConfig = mbpMqttConfigService.getById(id);
		if(mbpMqttConfig==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mbpMqttConfig);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "主题-通过主表ID查询")
	@ApiOperation(value="主题-通过主表ID查询", notes="主题-通过主表ID查询")
	@GetMapping(value = "/queryMbpMqttTopicsByMainId")
	public Result<?> queryMbpMqttTopicsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<MbpMqttTopics> mbpMqttTopicsList = mbpMqttTopicsService.selectByMainId(id);
		IPage <MbpMqttTopics> page = new Page<>();
		page.setRecords(mbpMqttTopicsList);
		page.setTotal(mbpMqttTopicsList.size());
		return Result.OK(page);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mbpMqttConfig
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MbpMqttConfig mbpMqttConfig) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<MbpMqttConfig> queryWrapper = QueryGenerator.initQueryWrapper(mbpMqttConfig, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<MbpMqttConfig> queryList = mbpMqttConfigService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<MbpMqttConfig> mbpMqttConfigList = new ArrayList<MbpMqttConfig>();
      if(oConvertUtils.isEmpty(selections)) {
          mbpMqttConfigList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          mbpMqttConfigList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<MbpMqttConfigPage> pageList = new ArrayList<MbpMqttConfigPage>();
      for (MbpMqttConfig main : mbpMqttConfigList) {
          MbpMqttConfigPage vo = new MbpMqttConfigPage();
          BeanUtils.copyProperties(main, vo);
          List<MbpMqttTopics> mbpMqttTopicsList = mbpMqttTopicsService.selectByMainId(main.getId());
          vo.setMbpMqttTopicsList(mbpMqttTopicsList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "MQTT配置信息列表");
      mv.addObject(NormalExcelConstants.CLASS, MbpMqttConfigPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("MQTT配置信息数据", "导出人:"+sysUser.getRealname(), "MQTT配置信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<MbpMqttConfigPage> list = ExcelImportUtil.importExcel(file.getInputStream(), MbpMqttConfigPage.class, params);
              for (MbpMqttConfigPage page : list) {
                  MbpMqttConfig po = new MbpMqttConfig();
                  BeanUtils.copyProperties(page, po);
                  mbpMqttConfigService.saveMain(po, page.getMbpMqttTopicsList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
