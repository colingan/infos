<#include "../header/header.ftl">
<link href="/src/home/home.css" rel="stylesheet" />

<div class="banner-container carousel slide" id="estart-banner-carousel" data-ride="carousel">
    <div class="estart-banner">
        <ol class="carousel-indicators">
            <#if model.banner?size gt 0>
                <#list model.banner as ban>
                    <li data-target="#estart-banner-carousel" data-slide-to="${ban_index}"></li>
                </#list>
            </#if>
        </ol>
        <ul class="banner-content carousel-inner" role="listbox">
            <#if model.banner?size gt 0>
                <#list model.banner as ban>
                    <li class="item">
                        <img src="${ban}" width=1200 height=400>
                    </li>
                </#list>
            </#if>
        </ul>
        <a class="focus-arrow left-focus-arrow" href="#estart-banner-carousel" data-slide="prev"></a>
        <a class="focus-arrow right-focus-arrow" href="#estart-banner-carousel"  data-slide="next"></a>
    </div>
</div>

<div class="service-title">
    <span>商业孵化平台都提供哪些服务?</span>
</div>
<!-- basic service -->
<div class="basic-service estart-service">
    <div class="estart-service-title">
        <span></span>
        <div class="basic-icon estart-service-icon"></div>
        <span></span>
    </div>
    <div class="tool-scroll" id="basic-scroll">
        <div class="wrap">
            <em class="lf"><b class="lf-icon"></b></em>
            <em class="rg"><b class="rg-icon"></b></em>
            <ul class="basic-content service-content">
                <#list model.services.basic as basicService>
                    <#if basicService??>
                        <li class="service-content-unhover">
                            <a href="/service/info?id=${basicService.id}&type=basic" data-log="{'target':'view-home-service-detail','id':'${basicService.id}'}" class="service-content-link">
                                <h3><b>${basicService.name}</b></h3>
                                <div class="service-icon basic-icon-lit basic-icon-${basicService_index}" style="background: url(${basicService.icon}) no-repeat"></div>
                                <script language="JavaScript" >
                                    $('.service-content li').mouseover(function(){
                                        $(this).children('a').children('.basic-icon-${basicService_index}').css('background','url(${basicService.hoverIcon}) no-repeat');
                                    }).mouseout(function(){
                                        $(this).children('a').children('.basic-icon-${basicService_index}').css('background','url(${basicService.icon}) no-repeat');
                                    });
                                </script>
                                <p class="service-abstract">
                                    ${basicService.abstract}
                                </p>
                                <a href="/service/info?id=${basicService.id}&type=basic" data-log="{'target':'view-home-service-detail','id':'${basicService.id}'}">查看详情</a>
                            </a>
                        </li>
                    </#if>
                </#list>
            </ul>
        </div>
    </div>
</div>
<!-- put in record -->
<div class="record-service estart-service">
    <div class="estart-service-title">
        <span></span>
        <div class="record-icon estart-service-icon"></div>
        <span></span>
    </div>
    <ul class="record-content service-content">
        <#list model.services.backup as backupService>
            <#if backupService??>
                <li class="service-content-unhover">
                    <a href="/backup/info#${backupService.name}" data-log="{'target':'view-home-service-detail','id':'${backupService.id}'}" class="service-content-link">
                        <h3><b>${backupService.name}</b></h3>
                        <div class="service-icon backup-icon backup-icon-${backupService_index}" style="background: url(${backupService.icon}) no-repeat"></div>
                        <script language="JavaScript" > 
                            $('.service-content li').mouseover(function(){
                                $(this).children('a').children('.backup-icon-${backupService_index}').css('background','url(${backupService.hoverIcon}) no-repeat')
                            }).mouseout(function(){
                                $(this).children('a').children('.backup-icon-${backupService_index}').css('background','url(${backupService.icon}) no-repeat')
                            })
                        </script>
                        <p class="service-abstract">
                            ${backupService.abstract}
                        </p>
                        <a href="/backup/info#${backupService.name}" data-log="{'target':'view-home-service-detail','id':'${backupService.id}'}">查看详情</a>
                    </a>
                </li>
            </#if>
        </#list>
    </ul>
</div>
<!-- tool and resource -->
<div class="tool-service estart-service">
    <div class="estart-service-title">
        <span></span>
        <div class="tool-icon estart-service-icon"></div>
        <span></span>
    </div>
    <div class="tool-scroll" id="tool-scroll">
        <div class="wrap">
            <em class="lf"><b class="lf-icon"></b></em>
            <em class="rg"><b class="rg-icon"></b></em>
            <ul class="tool-content service-content">
                <#list model.services.tools as toolsService>
                    <#if toolsService??>
                        <li class="service-content-unhover">
                            <a href="/service/info?id=${toolsService.id}&type=tools" data-log="{'target':'view-home-service-detail','id':'${toolsService.id}'}" class="service-content-link">
                                <h3><b>${toolsService.name}</b></h3>
                                <div class="service-icon tools-icon tools-icon-${toolsService_index}" style="background: url(${toolsService.icon}) no-repeat"></div>
                                <script language="JavaScript" > 
                                    $('.service-content li').mouseover(function(){
                                        $(this).children('a').children('.tools-icon-${toolsService_index}').css('background','url(${toolsService.hoverIcon}) no-repeat')
                                    }).mouseout(function(){
                                        $(this).children('a').children('.tools-icon-${toolsService_index}').css('background','url(${toolsService.icon}) no-repeat')
                                    })
                                </script>
                                <p class="service-abstract">
                                    ${toolsService.abstract}
                                </p>
                                <a href="/service/info?id=${toolsService.id}&type=tools" data-log="{'target':'view-home-service-detail','id':'${toolsService.id}'}">查看详情</a>
                            </a>
                        </li>
                    </#if>
                </#list>
            </ul>
        </div>
    </div>
</div>
<div class="successful-case">
    <div class="estart-service-title">
        <span></span>
        <div class="estart-service-icon estart-successful-icon"></div>
        <span></span>
    </div>
    <div class="case-content">
        <#if model.example?size gt 0>
            <#list model.example as example>
                <div class="each-case">
                    <img src="${example.image}"/>
                    <span>${example.name}</span>
                </div>
            </#list>
        </#if>
    </div>
</div>
<div class="estart-responsible-person">
    如有疑问，请联系estart@baidu.com
</div>
<#include "../footer/footer.ftl">
<script>
    require(['home/home']);
</script>
</body>
</html>
