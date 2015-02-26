define(['jquery'], function () {
	function setCurrent(cls) {
		$(cls).children('a').css('color', '#2DA1DC').end()
			.siblings('li').children('a').css('color', '#D5D6D8');
	}
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	if (location.href === location.origin) {
		setCurrent('.nav-index');
	}
	// 根据当前页面给导航加active样式
	if (location.href.indexOf('publish') !== -1) {
		setCurrent('#nav_publish');
	}else if (location.href.indexOf('admin') !== -1) {
		setCurrent('#nav_admin');
	}else if (location.href.indexOf('/blogs/root') !== -1) {
		var categoryId = getParameterByName('id');
		setCurrent('#nav_category_' + categoryId);
	}else if (location.href.indexOf('blogList') !== -1) {
		var categoryId = getParameterByName('rootCategory');
		setCurrent('#nav_category_' + categoryId);
	}else if(location.href.indexOf('/blogs/view') !== -1) {
		setCurrent('#nav_category_' + curRootCategory);
	}
	else {
		setCurrent('.nav-index');
	}
	// 悬浮出现导航下拉框
    $('.estart-navlist .nav-has-second').hover(function () {
        $(this).find('ul').slideDown();
        $(this).find('.dropdown-toggle').css('color','#2DA1DC');
    }, function () {
    	var that = $(this);
    	setTimeout(secondNavSlideUp(that), 100);
    });
    function secondNavSlideUp(that) {
    	that.find('ul').slideUp();
    	that.find('.dropdown-toggle').css('color','#D5D6D8');
    }
    // 鼠标悬浮时导航样式
    $('.estart-navlist .nav-has-second').mouseover(function () {
    	$(this).find('b.estart-caret').removeClass('estart-caret').addClass('estart-caret-up');
    }).mouseout(function () {
    	$(this).find('b.estart-caret-up').removeClass('estart-caret-up').addClass('estart-caret');
    })
})