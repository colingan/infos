<div class="footer">©2015 Baidu <a target="_blank" href="http://www.baidu.com/duty/">使用百度前必读</a> 京ICP证030173号</div>
<script src="/bower_components/requirejs/require.js"></script>
<script>
    require.config({
        baseUrl: '/src',
        urlArgs: '20141024',
        paths: {
            'jquery': '/bower_components/jquery/dist/jquery.min',
            'slider':'/bower_components/slider/slider',
            'bootstrap' : '/bower_components/bootstrap/dist/js/bootstrap.min',
            'jquery-ui': '/bower_components/jquery-ui/jquery-ui.min',
            'jtable': '/bower_components/jtable/jquery.jtable',
            'uploadify' : '/bower_components/uploadify/jquery.uploadify.min',
            'kindeditor' : '/kindeditor/kindeditor',
            'kindeditor-lang-CN' : '/kindeditor/lang/zh_CN',
            'jquery-validation': '/bower_components/jquery-validation/jquery.validate.min',
            'simple-color': '/bower_components/simple-color/jquery.simple-color',
            'plupload' : '/bower_components/plupload/js/plupload.full.min',
            'bootpag' : '/bower_components/bootpag/jquery.bootpag.min'
        },
        shim:{
        	'bootstrap': {
        		deps : ['jquery'],
        		exports: ''
        	},
        	'jquery-ui' : {
        		deps: ['jquery'],
        		exports: ''
        	},
        	'jtable' :{
        		deps: ['jquery-ui'],
        		exports: ''
        	},
        	'uploadify' : {
        		deps: ['jquery'],
        		exports: ''
        	},
        	'kindeditor' : {
        		deps: ['jquery'],
        		exports: 'KindEditor'
        	},
        	'kindeditor-lang-CN' : {
        		deps: ['kindeditor'],
        		exports: ''
        	},
        	'jquery-validation' : {
        		deps: ['jquery'],
        		exports: ''
        	},
        	'simple-color': {
        		deps: ['jquery'],
        		exports: ''
        	},
        	'plupload' : {
        		deps: ['jquery'],
        		exports: ''
        	},
        	'bootpag' : {
        		deps: ['jquery'],
        		exports: ''
        	}
        }
    });
    require(['jquery', 'bootstrap']);
    require(['common/js/nav']);
    
    require(['common/js/log'], function(log) {
        log.init('http://fclog.baidu.com/nirvana/log/fclogimg.gif', {
            username: '${model.basic.userName}'
        });

        var estartLogMark = 'ones123-log-';
        $('body').on('click.monitor', '[data-log]', function() {
            var params = $(this).data('log').replace(/\'/g, '"');
            params = JSON.parse(params);
            params.target = estartLogMark + params.target;
            log.log(params);
        });

        log.log({target: estartLogMark + 'page-enter', page: '${model.basic.tag}'});
    });
</script>