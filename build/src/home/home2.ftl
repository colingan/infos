<#include "../header/header.ftl">
<link href="/src/home/home.css" rel="stylesheet" />
<link href="/src/home/default.css" rel="stylesheet" />
<link href="/src/home/newhome.css" rel="stylesheet" />

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

<div id="container" style="margin-top: 30px">
	<div id="content" class="clearfix">
		<div id="main" class="col620 clearfix" role="main">
			<div class="item-wrap clearfix">
				<#if model.newBlogs?size gt 0>
					<#list model.newBlogs as rootEntry>
						<div class="item col300">
							<div class="item-cat">
								<span class="cat-links">
									<a rel="category" href="/blogs/root?id=${rootEntry.getKey().id}">${rootEntry.getKey().name}</a>
								</span>
							</div>
						</div>
						<div class="item-content">
							<ul>
								<#list rootEntry.getValue() as childEntry>
									<#list childEntry.getValue() as latestBlog>
										<li><a href="/blogs/view?id=${latestBlog.id}">${latestBlog.title}</a></li>
									</#list>
								</#list>
							</ul>
						</div>
					</#list>
				</#if>
			</div>
		</div>
		
		<div id="sidebar" class="widget-area col300" role="complementary">
			<div id="social-media" class="clearfix"></div>
			<aside id="search-3" class="widget widget_search">
				<form id="searchform" class="searchform" method="get" role="search">
					<div>
						<label class="screen-reader-text" for="s">Search for:</label>
						<input id="s" type="text" name="s" />
						<input id="searchsubmit" type="submit" />
					</div>
				</form>
			</aside>
			<!--user infos -->
			<aside id="userinfos" class="widget widget_recent_entries">
				<h2 class="widget-title">${model.basic.userName}</h2>
			</aside>
		</div>
	</div>
	
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
	
<#include "../footer/footer.ftl">
<script>
    require(['home/home']);
</script>
</body>
</html>
