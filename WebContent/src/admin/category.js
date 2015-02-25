define(['require', 'jtable', 'simple-color', 'plupload'], function (require) {
	var $table = $('#categoryTableContainer');
	var uploader;
	
	function newPicker(value) {
		$picker = $('<div class="rectangle" style="background:' + value + ';"></div>')
		return $picker;
	}
	
	function removeFile(fielid) {
		uploader.removeFile(fileid);
	}
	
	$table.jtable({
		title: '一级分类列表',
		paging: false,	// 无分页
		sorting: false,	// 无排序
		actions: {
			listAction: '/admin/rootCategoryList',
			deleteAction: '/admin/delCategory',
			updateAction: '/admin/updateCategory',
			createAction: '/admin/addCategory'
		},
		fields: {
			id : {
				key: true,
				create: false,
				edit: false,
				list: false
			},
			// child table
			Child: {
				title: '',
				width: '5%',
				sorting: false,
				edit: false,
				create: false,
				display: function(categoryData) {
					// create an image that will be used to open child table
					var $img = $('<img src="/src/admin/icon/note.png" title="子分类编辑" />');
					// Open child table when user clicks the image
					$img.click(function(){
						$table.jtable('openChildTable', $img.closest('tr'), {
									title: categoryData.record.name + ' - 子分类',
									actions: {
										listAction: '/admin/childCategoryList?parentCategory=' + categoryData.record.id,
										deleteAction: '/admin/delCategory',
										updateAction: '/admin/updateCategory',
										createAction: '/admin/addCategory'
									},
									fields: {
										parentCategory: {
											type: 'hidden',
											defaultValue: categoryData.record.id
										},
										id: {
											key: true,
											create: false,
											edit: false,
											list: false
										},
										name: {
											title: '分类名称',
											width: '20%'
										},
										icon: {
											title: 'icon',
											width: '20%',
											display: function(categoryData) {
												return $('<img src=/onesfile/categoryIcons/' + categoryData.record.icon + ' width="48" height="48" />');
											},
											input: function(data) {
												return '<div id="filelist"></div><div id="container"><a id="iconfile" href="javascript:;">选择图标</a><a id="uploadfile" href="javascript:;">上传文件</a></div><input type="hidden" name="icon" id="icon" /><br/><pre id="console"></pre>';
											}
										},
										level: {
											title: 'level',
											list: false,
											type: 'hidden',
											defaultValue : '2',
											edit: false
										}
									},
									formCreated: function(event, data){
										uploader = new plupload.Uploader({
											runtimes: 'html5,flash,html4',
											browse_button: 'iconfile',
											container: document.getElementById('container'),
											url: '/admin/category/imageUpload',
											flash_swf_url: '/bower_components/plupload/js/Moxie.swf',
											multi_selection: false,
											
											filters : {
												max_file_size : '10mb',
												mime_types: [
													{title : "Image files", extensions : "jpg,gif,png"}
												]
											},
											
											init: {
												PostInit: function() {
													document.getElementById('filelist').innerHTML = '';

													document.getElementById('uploadfile').onclick = function() {
														uploader.start();
														return false;
													};
												},

												FilesAdded: function(up, files) {
													var MAX_UPLOAD_FILES = 1;
													plupload.each(files, function(file) {
														if(up.files.length > MAX_UPLOAD_FILES) {
															alert("You are allowed to add only " + MAX_UPLOAD_FILES + " files.");
															up.removeFile(file);
															return;
														}
														document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
													});
												},

												UploadProgress: function(up, file) {
													document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
												},

												Error: function(up, err) {
													document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
												},
												
												FileUploaded: function(up, file){
													$('#icon').val(file.name);
												}
											}
										});
										uploader.init();
									}
								}, function(data) {
									// opened handler
									data.childTable.jtable('load');
								});
					});
					// return image to show on the first level category table row
					return $img;
				}
			},
			name: {
				title: '分类名称',
				width: '20%'
			},
			icon: {
				title: 'icon',
				width: '20%',
				display : function(categoryData) {
					return newPicker(categoryData.record.icon);
				},
				input: function(data) {
					if(data.record) {
						return '<input type="text" name="icon" id="icon" value="' + data.record.icon + '" />';
					}else{
						return '<input type="text" name="icon" id="icon" />';
					}
				}
			},
			level: {
				title: 'level',
				list: false,
				type: 'hidden',
				edit: false,
				defaultValue : '1'
			},
			parentCategory: {
				title: '父分类',
				list: false,
				edit: false,
				type: 'hidden',
				defaultValue: '0'
			}
		},
		formCreated: function(event, data){
			data.form.css("width", "300px");
			data.form.css("height", "250px");
			$('#icon').simpleColor();
		}
	});
	
	// load first level category list from server
	$('#categoryTableContainer').jtable('load');
	
})