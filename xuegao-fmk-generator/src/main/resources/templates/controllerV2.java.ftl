package ${package.Controller};

import com.xuegao.core.model.Result;
import com.xuegao.mapper.model.GenericModelDTO;
import com.xuegao.mapper.model.PageResult;
import com.xuegao.wechatservermonolith.common.model.sysuser.doo.SysUser;
import com.xuegao.wechatservermonolith.sysuser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ${package.Service}.${table.serviceImplName};
import ${package.Entity}.${entity};

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

import java.util.List;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#--<#if kotlin>-->
<#--class ${table.controllerName}<#if superControllerClass??>:${superControllerClass}()</#if>-->
<#--<#else>-->
<#if superControllerClass??>public class ${table.controllerName} extends ${superControllerClass}{
<#else>public class ${table.controllerName} {
<#--</#if>-->

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ${table.serviceImplName} ${(table.serviceImplName?uncap_first)};

    @RequestMapping(value = "/${entity?uncap_first}/save", method = {RequestMethod.POST})
    public Result<${entity}> save(@RequestBody ${entity} ${entity?uncap_first}) {
        return Result.success(${entity?uncap_first}Service.insertService(${entity?uncap_first}));
    }

    @RequestMapping(value = "/${entity?uncap_first}/update", method = {RequestMethod.POST})
    public Result<${entity}> update(@RequestBody ${entity} ${entity?uncap_first}) {
        return Result.success(${entity?uncap_first}Service.updateService(${entity?uncap_first}));
    }

    @RequestMapping(value = "/${entity?uncap_first}/batchDisable", method = {RequestMethod.POST})
    public Result<?> batchDisable(@RequestBody GenericModelDTO<${entity}> dto) {
        return Result.success(${entity?uncap_first}Service.batchDisableService(dto));
    }

    @RequestMapping(value = "/${entity?uncap_first}/list", method = {RequestMethod.POST})
    public Result<List<${entity}>> list(@RequestBody GenericModelDTO<${entity}> dto) {
        return Result.success(${entity?uncap_first}Service.listService(dto));
    }

    @RequestMapping(value = "/${entity?uncap_first}/search", method = {RequestMethod.POST})
    public PageResult<${entity}> search(@RequestBody GenericModelDTO<${entity}> dto) {
        return PageResult.ok(${entity?uncap_first}Service.searchService(dto));
    }

}
</#if>