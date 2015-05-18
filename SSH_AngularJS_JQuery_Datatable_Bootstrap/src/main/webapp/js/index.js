$(document).ready(function() {

	$("#example").dataTable({
		dom:
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-3'i><'col-sm-3'l><'col-sm-6'p>>",
		"processing" : true,
		"serverSide" : true,
		"length" : 5,
		"paging" : true,
		"ordering" : true,
		"pageLength" : 5,
		"paginationType" : "full_numbers",
		"lengthMenu" : [ 5, 10, 25, 50, 75, 100 ],
		"ajax" : {
			"url" : "persons.action",
			"type" : "POST"
		},
		"columns" : [ {
			"title" : "ID",
			"data" : "id",
			"visible" : true,
			"orderable" : true,
			"render" : function(data, type, full, meta){
				return '<a href="/">' + data +'</a>';
			}
		}, {
			"title" : "NAME",
			"data" : "name",
			"visible" : true,
			"orderable" : true
		}, {
			"title" : "BIRTHDAY",
			"data" : "birth",
			"visible" : true,
			"orderable" : true
		}, {
			"title" : "GENDER",
			"data" : "gender",
			"visible" : true,
			"orderable" : true
		} ],
		"language" : {
			"emptyTable" : "没有数据",
			"info" : "显示 第 _START_ 到 _END_ 条数据  总共 _TOTAL_ 条数据 ",
			"infoEmpty" : "没有数据",
			"infoFiltered" : "(filtered from _MAX_ total entries)",
			"infoPostFix" : "",
			"thousands" : ",",
			"lengthMenu" : "每页显示条数 _MENU_ ",
			"loadingRecords" : "加载中...",
			"processing" : "处理中...",
			"search" : "搜索:",
			"zeroRecords" : "找不到匹配的数据",
			"paginate" : {
				"first" : "首页",
				"last" : "尾页",
				"next" : "下页",
				"previous" : "上页"
			}
		}
	});

});