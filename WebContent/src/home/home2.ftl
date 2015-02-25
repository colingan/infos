<#include "../header/header.ftl">
<link href="/src/home/home.css" rel="stylesheet" />
<link href="/bower_components/VerticalTimeline/css/default.css" rel="stylesheet" />
<link href="/bower_components/VerticalTimeline/css/component.css" rel="stylesheet" />

<#if model.banner?? && model.banner?size gt 0>
	<div class="banner-container carousel slide" id="estart-banner-carousel" data-ride="carousel">
		<div class="estart-banner">
			<ol class="carousel-indicators">
				<#list model.banner as ban>
					<li data-target="#estart-banner-carousel" data-slide-to="${ban_index}"></li>
				</#list>
			</ol>
			<ul class="banner-content carousel-inner" role="listbox">
				<#list model.banner as ban>
					<li class="item">
                        <img src="/onesfile/sliders/${ban}" width=1200 height=400 />
                    </li>
				</#list>
			</ul>
			<a class="focus-arrow left-focus-arrow" href="#estart-banner-carousel" data-slide="prev"></a>
			<a class="focus-arrow right-focus-arrow" href="#estart-banner-carousel"  data-slide="next"></a>
		</div>
	</div>
</#if>

<div class="main">
	<!-- blogs -->
	<#if model.newBlogs?size gt 0>
		<ul class="cbp_tmtimeline">
			<#list model.newBlogs as rootEntry>
				<li>
					<div class="cbp_tmtime"><span><a href="/blogs/root?id=${rootEntry.getKey().id}" style="color:#000000;">${rootEntry.getKey().name}</a></span></div>
					<div class="cbp_tmicon" style="background:${rootEntry.getKey().icon};"></div>
					<div class="cbp_tmlabel">
                        <#assign started=false />
                        <#assign closed=true />
						<#list rootEntry.getValue() as childEntry>
                            <#assign started=true />
                            <#assign closed=false />
							<#if childEntry_index % 3 == 0>
								<div class="blog-list-panel">
							</#if>
							<div style="width:32%;float:left;">
								<h2 style='background-image: url("/onesfile/categoryIcons/${childEntry.getKey().icon}");background-repeat: no-repeat;height: 50px; padding-left: 50px;padding-top: 5px; background-size: 48px 48px;'><a href="/blogList?rootCategory=${rootEntry.getKey().id}&childCategory=${childEntry.getKey().id}">${childEntry.getKey().name}</a></h2>
								<!-- latest blogs -->
								<#list childEntry.getValue() as latestBlog>
									<p class="cloud-des"><a href="/blogs/view?id=${latestBlog.id}">${latestBlog.title}</a></p>
								</#list>
								<p class="check-more">
									<a href="/blogList?rootCategory=${rootEntry.getKey().id}&childCategory=${childEntry.getKey().id}"> 查看更多>> </a>
								</p>
							</div>
							<#if childEntry_index % 3 == 2>
								</div>
                                <#assign closed=true />
							</#if>
						</#list>
                        <#if started && closed == false>
                            </div>
                        </#if>
			        </div>
				</li>
			</#list>
		</ul>
	</#if>
	<!-- end of blogs -->
	
	<!-- links -->
	<div id="linkDiv">
		<h3>友情链接</h3>
		<hr>
		<#if model.links??>
			<p>
				<#list model.links as link>
					<a target="_blank" href="${link.link}" style="color:#000000;">${link.name}</a>
				</#list>
			</p>
		</#if>
	</div>
	<!-- end of links -->
</div>

<div class="estart-responsible-person">
 如有问题，请联系 ones_web@baidu.com
</div>
<#include "../footer/footer.ftl">
<script>
    require(['home/home']);
</script>
</body>
</html>
