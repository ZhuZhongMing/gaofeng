package org.jeecg.modules.system.controller;

import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.MbpDevEquipment;
import org.jeecg.modules.system.entity.MbpMqttData;
import org.jeecg.modules.system.service.IMbpDevEquipmentService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.system.vo.MbpDevEquipmentVO;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 生产设备
 * @Author: jeecg-boot
 * @Date:   2020-12-04
 * @Version: V1.0
 */
@Api(tags="生产设备")
@RestController
@RequestMapping("/system/mbpDevEquipment")
@Slf4j
public class MbpDevEquipmentController extends JeecgController<MbpDevEquipment, IMbpDevEquipmentService> {
	@Autowired
	private IMbpDevEquipmentService mbpDevEquipmentService;


	 /**
	  * 查询生产设备最新数据
	  *
	  * @param mbpDevEquipment
	  * @return
	  */
	 //@AutoLog(value = "生产设备-查询生产设备最新数据")
	 @ApiOperation(value="生产设备-查询生产设备最新数据", notes="生产设备-查询生产设备最新数据")
	 @GetMapping(value = "/queryLastVarValue")
	 public Result<?> queryLastVarValue(MbpDevEquipment mbpDevEquipment) {
		 List<MbpMqttData> list = mbpDevEquipmentService.queryLastVarValue(mbpDevEquipment);
		 List<MbpMqttData> temperatureList = mbpDevEquipmentService.queryLastTemperature(mbpDevEquipment);
		 MbpDevEquipmentVO vo = new MbpDevEquipmentVO();
		 vo.setId(mbpDevEquipment.getId());
		 vo.setLiquidLevel("80");
		 vo.setCarbonDioxide("1");
		 list.forEach(item -> {
		 	if (item.getVarName().contains("Temperature")) {
				//温度
				vo.setTemperature(item.getVarValue());
				return;
			}
		 	if (item.getVarName().contains("WaterValveClose")) {
				//水阀关
				vo.setWaterValveClose(item.getVarValue());
				return;
			}
			 if (item.getVarName().contains("WaterValveOpen")) {
				 //水阀开
				 vo.setWaterValveOpen(item.getVarValue());
				 return;
			 }
			 if (item.getVarName().contains("CloseTime")) {
				 //关时间
				 vo.setCloseTime(item.getVarValue());
				 return;
			 }
			 if (item.getVarName().contains("OpenTime")) {
				 //开时间
				 vo.setOpenTime(item.getVarValue());
				 return;
			 }
			 if (item.getVarName().contains("ActualMining")) {
				 //实采
				 vo.setActualMining(item.getVarValue());
				 return;
			 }
			 if (item.getVarName().contains("Return")) {
				 //回差
				 vo.setReturnDifference(item.getVarValue());
				 return;
			 }
		 });
		 temperatureList.forEach(item -> {
			 if (item.getVarName().equals("Ice_Water_Temperature")) {
				 //冰水温度
				 vo.setIceWaterTemperature(item.getVarValue());
				 return;
			 }
			 if (item.getVarName().equals("Saccharification_Temperature")) {
				 //糖化温度
				 vo.setSaccharificationTemperature(item.getVarValue());
				 return;
			 }
			 if (item.getVarName().equals("Filtration_Temperature")) {
				 //过滤温度
				 vo.setFiltrationTemperature(item.getVarValue());
				 return;
			 }
		 });
		 return Result.OK(vo);
	 }

	 /**
	  * 查询生产设备温度
	  *
	  * @param mbpDevEquipment
	  * @return
	  */
	 //@AutoLog(value = "生产设备-查询生产设备温度")
	 @ApiOperation(value="生产设备-查询生产设备温度", notes="生产设备-查询生产设备温度")
	 @GetMapping(value = "/queryDevEquipmentList")
	 public Result<?> queryDevEquipmentList(MbpDevEquipment mbpDevEquipment) {
		 List<MbpDevEquipmentVO> list = mbpDevEquipmentService.queryDevEquipmentList(mbpDevEquipment);
		 list.forEach(item -> {
		 	item.setLiquidLevel("80%");
		 	item.setCarbonDioxide("1%");
		 });
		 return Result.OK(list);
	 }

	 /**
	  * 查询所有生产设备温度变化
	  *
	  * @param mbpDevEquipment
	  * @return
	  */
	 //@AutoLog(value = "生产设备-查询生产设备温度")
	 @ApiOperation(value="生产设备-查询生产设备温度", notes="生产设备-查询生产设备温度")
	 @GetMapping(value = "/queryAllDevEquipmentList")
	 public Result<?> queryAllDevEquipmentList(MbpDevEquipment mbpDevEquipment) {
		 List<MbpDevEquipmentVO> list = mbpDevEquipmentService.queryAllDevEquipmentList(mbpDevEquipment);
		 List<MbpDevEquipment> devList = mbpDevEquipmentService.list();

		 Map<String,List<MbpDevEquipmentVO>> map = new HashMap();

		 devList.forEach(item -> {
			 List<MbpDevEquipmentVO> arr = new ArrayList<>();
			map.put(item.getId(), arr);
		 });

		 list.forEach(item -> {
		 	map.get(item.getEquipmentId()).add(item);
		 });

		 List<List<MbpDevEquipmentVO>> resultList = new ArrayList<>();
		 map.forEach((key,value) -> {
			 resultList.add(value);
		 });

		 return Result.OK(resultList);
	 }
	
	/**
	 * 分页列表查询
	 *
	 * @param mbpDevEquipment
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "生产设备-分页列表查询")
	@ApiOperation(value="生产设备-分页列表查询", notes="生产设备-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MbpDevEquipment mbpDevEquipment,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MbpDevEquipment> queryWrapper = QueryGenerator.initQueryWrapper(mbpDevEquipment, req.getParameterMap());
		Page<MbpDevEquipment> page = new Page<MbpDevEquipment>(pageNo, pageSize);
		IPage<MbpDevEquipment> pageList = mbpDevEquipmentService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mbpDevEquipment
	 * @return
	 */
	@AutoLog(value = "生产设备-添加")
	@ApiOperation(value="生产设备-添加", notes="生产设备-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MbpDevEquipment mbpDevEquipment) {
		mbpDevEquipmentService.save(mbpDevEquipment);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mbpDevEquipment
	 * @return
	 */
	@AutoLog(value = "生产设备-编辑")
	@ApiOperation(value="生产设备-编辑", notes="生产设备-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MbpDevEquipment mbpDevEquipment) {
		mbpDevEquipmentService.updateById(mbpDevEquipment);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "生产设备-通过id删除")
	@ApiOperation(value="生产设备-通过id删除", notes="生产设备-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mbpDevEquipmentService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "生产设备-批量删除")
	@ApiOperation(value="生产设备-批量删除", notes="生产设备-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mbpDevEquipmentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "生产设备-通过id查询")
	@ApiOperation(value="生产设备-通过id查询", notes="生产设备-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MbpDevEquipment mbpDevEquipment = mbpDevEquipmentService.getById(id);
		if(mbpDevEquipment==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mbpDevEquipment);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mbpDevEquipment
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MbpDevEquipment mbpDevEquipment) {
        return super.exportXls(request, mbpDevEquipment, MbpDevEquipment.class, "生产设备");
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
        return super.importExcel(request, response, MbpDevEquipment.class);
    }

}
