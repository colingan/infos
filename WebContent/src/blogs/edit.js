define(['kindeditor', 'kindeditor-lang-CN', 'jquery-validation'], function () {
	var K = window.KindEditor;
	var editor = K.create('#service-edit',{
            uploadJson : '/editor/upload.do',
            allowFileManager : false
        });
    editor.html(content_string);
    
    $('#blog-form').validate({
    	errorClass : "warning",
    	onkeyup : false,
    	onblur : false,
    	submitHandler : function() {
    		editor.sync();
    		$.ajax({
    			cache: false,
    			type: "POST",
    			url: "/publish/save",
    			data: $('#blog-form').serialize(),
    			async: false,
    			error: function(request) {
    				alert("提交失败！");
    			},
    			success: function(data) {
    				var obj = eval(data);
    				if(obj.Result != "OK"){
    					alert(obj.Message);
    				}else{
    					window.location.href = obj.Redirect;
    				}
    			}
    		});
    	}
    });
})