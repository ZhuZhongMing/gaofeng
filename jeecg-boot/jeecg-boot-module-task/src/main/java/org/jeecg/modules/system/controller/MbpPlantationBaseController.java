package org.jeecg.modules.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.MbpPlantationBase;
import org.jeecg.modules.system.service.IMbpPlantationBaseService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
 * @Description: 种植基地
 * @Author: jeecg-boot
 * @Date:   2020-12-15
 * @Version: V1.0
 */
@Api(tags="种植基地")
@RestController
@RequestMapping("/system/mbpPlantationBase")
@Slf4j
public class MbpPlantationBaseController extends JeecgController<MbpPlantationBase, IMbpPlantationBaseService> {
	@Autowired
	private IMbpPlantationBaseService mbpPlantationBaseService;

	 /**
	  *  全查询
	  *
	  * @param mbpPlantationBase
	  * @param req
	  * @return
	  */
	 @ApiOperation(value="种植基地-全查询", notes="种植基地-全查询")
	 @GetMapping(value = "/listAll")
	 public Result<?> listAll(MbpPlantationBase mbpPlantationBase, HttpServletRequest req) {
		 QueryWrapper<MbpPlantationBase> queryWrapper = QueryGenerator.initQueryWrapper(mbpPlantationBase, req.getParameterMap());
		 List<MbpPlantationBase> pageList = mbpPlantationBaseService.list(queryWrapper);
		 return Result.OK(pageList);
	 }
	
	/**
	 * 分页列表查询
	 *
	 * @param mbpPlantationBase
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	// @AutoLog(value = "种植基地-分页列表查询")
	@ApiOperation(value="种植基地-分页列表查询", notes="种植基地-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MbpPlantationBase mbpPlantationBase,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MbpPlantationBase> queryWrapper = QueryGenerator.initQueryWrapper(mbpPlantationBase, req.getParameterMap());
		Page<MbpPlantationBase> page = new Page<MbpPlantationBase>(pageNo, pageSize);
		IPage<MbpPlantationBase> pageList = mbpPlantationBaseService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mbpPlantationBase
	 * @return
	 */
	@AutoLog(value = "种植基地-添加")
	@ApiOperation(value="种植基地-添加", notes="种植基地-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MbpPlantationBase mbpPlantationBase) {
		mbpPlantationBaseService.save(mbpPlantationBase);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mbpPlantationBase
	 * @return
	 */
	@AutoLog(value = "种植基地-编辑")
	@ApiOperation(value="种植基地-编辑", notes="种植基地-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MbpPlantationBase mbpPlantationBase) {
		mbpPlantationBaseService.updateById(mbpPlantationBase);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "种植基地-通过id删除")
	@ApiOperation(value="种植基地-通过id删除", notes="种植基地-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mbpPlantationBaseService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "种植基地-批量删除")
	@ApiOperation(value="种植基地-批量删除", notes="种植基地-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mbpPlantationBaseService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "种植基地-通过id查询")
	@ApiOperation(value="种植基地-通过id查询", notes="种植基地-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MbpPlantationBase mbpPlantationBase = mbpPlantationBaseService.getById(id);
		if(mbpPlantationBase==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mbpPlantationBase);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mbpPlantationBase
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MbpPlantationBase mbpPlantationBase) {
        return super.exportXls(request, mbpPlantationBase, MbpPlantationBase.class, "种植基地");
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
        return super.importExcel(request, response, MbpPlantationBase.class);
    }

}
