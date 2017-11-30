Ext.namespace("Ctgu");
Ext.namespace("Ctgu.frontdesk");
Ctgu.frontdesk.userGrid = function() {
	Ctgu.frontdesk.userGrid.superclass.constructor.call(this);
}
Ext.apply(Ext.form.VTypes, {
			cardIDCheck : function(val, field) {
				var reg = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
				if (reg.test(val) == false) {
					return false;
				} else {
					return true;
				}
			},
			cardIDCheckText : "身份证号输入错误，请重新输入！"
		});
var addUser = function() {
	var btnsubmitclick = function() {
		userPanel.getForm().submit({
					waitTitle : "请稍后",
					waitMsg : "正在保存客户数据，请稍后……",
					url : 'UserInfoServelet',
					method : "POST",
					params : {
						cmd : "save"
					},
					success : function(form, action) {
						Ext.Msg.alert("提示", "数据保存成功！");
						// Ext.getCmp("useWin").close();
						userWin.close();
						userStore.load();

					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "保存数据失败！");
					}
				});
	}
	var btnresetclick = function() {
		userPanel.getForm().reset();
	}
	var radiogroup = new Ext.form.RadioGroup({
				fieldLabel : '性别',
				width : 100,
				items : [{
							name : 'userSex',
							inputValue : true,
							boxLabel : '男',
							checked : true
						}, {
							name : 'userSex',
							inputValue : false,
							boxLabel : '女'
						}]
			});
	// 获取单选组的值
	radiogroup.on('change', function(rdgroup, checked) {
				// alert(checked.getRawValue());
			});
	var userPanel = new Ext.form.FormPanel({
				id : 'userPanel',
				border : false,
				labelWidth : 100,
				labelAlign : "right",
				buttonAlign : "center",
				frame : false,
				items : [{
							layout : 'column',
							border : false,
							items : [{
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										anchor : '50%',
										items : [ // 从上往下的布局
										{
													name : "loginName",
													fieldLabel : "登陆名",
													xtype : "textfield",
													
													listeners : { // 添加光标离开事件
														blur : function() {
															Ext.Ajax.request({
																		url : 'LoadUserInfoServelet',
																		params : {
																			userID : idse,
																			cmd : "checkLoginName",
																			loginName : Ext.getCmp('loginName').getValue()
																		},
																		method : "POST",
																		success : function(response, opts) {
																			if (response.responseText == "true") {
																				Ext.Msg.alert("提示", "此用户名已被占用，请使用其它用户名！");
																			} else {
																			}
																		},
																		failure : function(response, opts) {
																			Ext.Msg.alert("提示", "检查用户名是否可用时出现异常，请联系管理员！");
																		}
																	});
														}
													}
												}]
									}, {
										columnWidth : .5, // 该列有整行中所占百分比
										anchor : '50%',
										layout : "form",
										border : false,
										items : [{
													name : "userName",
													fieldLabel : "姓名",
													
													xtype : "textfield"
												}]
										// items : [radiogroup]
								}]
						}, {
							layout : 'column',
							border : false,
							items : [{
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										anchor : '50%',
										border : false,
										items : [{
													name : "userAge",
													fieldLabel : "年龄",
													maxValue : 130,
													minValue : 0,
													xtype : "numberfield",
													maxText : "年龄应小于130岁",
													
													minText : "年龄应大于0岁"
												}]
									}, {
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										anchor : '50%',
										items : [radiogroup]
									}]
						}, {
							layout : 'column',
							
							border : false,
							items : [{
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										anchor : '50%',
										items : [{
													xtype : 'combo',
													fieldLabel : '用户类型',
													name : 'userType',
													emptyText : '请选择',
													mode : 'local',
													store : new Ext.data.SimpleStore({
																fields : ['value', 'text'],
																data : [['1', '管理员'], ['0', '普通用户']]
															}),
													editable : false,
													triggerAction : 'all',
													valueField : 'value',
													displayField : 'text'
												}]
									}, {
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										anchor : '50%',
										items : [{
													name : "userTel",
													fieldLabel : "联系方式",
													xtype : "numberfield",
													maxLength : 11,
													minLength : 11,
													maxLengthText : '电话号码长度应为11位',
													minLengthText : '电话号码长度应为11位'
												}]
									}]
						}, {
							layout : 'column',
							border : false,
							items : [{
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										anchor : '50%',
										items : [{
													name : "userAddress",
													fieldLabel : "地址",
													xtype : "textfield"
												}]
									}
							// , {
							// columnWidth : .5, // 该列有整行中所占百分比
							// layout : "form",
							// border : false,
							// items : [{
							// name : "guestZip",
							// fieldLabel : "邮编",
							// xtype : "numberfield",
							// maxLength : 6,
							// minLength : 6,
							// maxLengthText : '邮政编码长度应为6位',
							// minLengthText : '邮政编码长度应为6位'
							// }]
							// }
							]
						}
				// , {
				// name : "guestBirthday",
				// fieldLabel : "生日",
				// xtype : "datefield",
				// format : "Y-m-d",
				// anchor : '50%'
				// }
				],
				buttons : [{
							text : "保存",
							handler : btnsubmitclick,
							scope : this
						}, {
							text : "重置",
							handler : btnresetclick,
							scope : this
						}]
			});
	var userWin = new Ext.Window({
				title : '新增用户信息',
				items : [userPanel],
				width : 600,
				height : 190,
				id : "useWin"
			});
	userWin.show();
	// Ext.Msg.alert("哈哈");
};
var resetPassword = function() {
	var grid = Ext.getCmp("userGrid");
	//
	var selectionModel = grid.getSelectionModel();
	if (selectionModel.getCount() == 0) {
		Ext.Msg.alert("提示", "您还未选择需要重置密码的记录，请先选择一条记录！");
	} else {
		var record = grid.getSelectionModel().getSelected();
		Ext.Ajax.request({
					waitTitle : "请稍后",
					waitMsg : "正在重置密码，请稍后……",
					url : 'UserInfoServelet',
					method : "POST",
					params : {
						cmd : "resetPassword",
						userID : record.get('userID')
					},
					success : function(form, action) {
						Ext.Msg.alert("提示", "密码已被重置，初始密码为112233！");
						// Ext.getCmp("useWin").close();
						// userWin.close();
						userStore.load();

					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "密码重置失败，请联系管理员！");
					}
				});
	};
	
};
var deleteUser = function() {
		var grid = Ext.getCmp("userGrid");
		//
		var selectionModel = grid.getSelectionModel();
		if (selectionModel.getCount() == 0) {
			Ext.Msg.alert("提示", "您还未选择需要删除的记录，请先选择一条记录！");
		} else {
			Ext.Msg.confirm("提示", "数据删除后无法恢复，是否确认删除？", function(btn) {
						if (btn == "yes") {
							var record = grid.getSelectionModel().getSelected();
							Ext.Ajax.request({
										waitTitle : "请稍后",
										waitMsg : "正在删除用户数据，请稍后……",
										url : 'UserInfoServelet',
										method : "POST",
										params : {
											cmd : "delete",
											userID : record.get('userID')
										},
										success : function(form, action) {
											Ext.Msg.alert("提示", "数据删除成功！");
											// Ext.getCmp("useWin").close();
											// userWin.close();
											userStore.load();

										},
										failure : function(form, action) {
											Ext.Msg.alert("提示", "数据删除失败！");
										}
									});
						}
					})
		}
	}
var userStore = new Ext.data.JsonStore({
			root : "userList",
			url : "UserDataServelet",
			idProperty : "userId",
			totalProperty : "rowCount",
			fields : [{
						name : 'userID',
						type : 'int'
					}, "loginName", "userName", {
						name : "userSex"
						// type : "boolean"
				}	, {
						name : "userAge",
						type : 'int'
					}, "userType", "userTel", "userAddress"]
		});
var toolbar = new Ext.Toolbar([{
			text : "新增用户",
			handler : addUser
		}, '-', {
			text : "重置密码",
			handler : resetPassword
		}, '-', {
			text : "删除用户",
			handler : deleteUser
		}]);

Ctgu.frontdesk.userGrid = Ext.extend(Ext.grid.GridPanel, {
			id : 'userGrid',
			// height:120
			// ,layout:'column'
			title : "用户管理",
			store : userStore,
			tbar : toolbar,
			initComponent : function() {
				Ext.apply(this, {
							columns : [{
										header : "用户ID",
										dataIndex : "userID",
										width : 60
									}, {
										header : "登录名",
										dataIndex : "loginName",
										sortable : true,
										width : 100
									}, {
										header : "用户姓名",
										dataIndex : "userName",
										sortable : true,
										width : 100
									}, {
										header : "用户性别",
										dataIndex : "userSex",
										width : 60
									}, {
										header : "用户年龄",
										dataIndex : "userAge",
										width : 60
									}, {
										header : "用户类型",
										dataIndex : "userType",
										width : 60
									}, {
										header : "联系方式",
										dataIndex : "userTel",
										width : 100
									}, {
										header : "住址",
										dataIndex : "userAddress",
										width : 150
									}],
							bbar : new Ext.PagingToolbar({
										store : userStore,
										pageSize : 5
									})
						});
				userStore.load();
				Ctgu.frontdesk.userGrid.superclass.initComponent.apply(this, arguments);
			},
			onRender : function() {
				Ctgu.frontdesk.userGrid.superclass.onRender.apply(this, arguments);
			},
			afterRender : function() {
				Ctgu.frontdesk.userGrid.superclass.afterRender.apply(this, arguments);
			}
		});
Ext.reg('userGrid', Ctgu.frontdesk.userGrid);
//Ext.onReady(function() {
//			var v = new Ctgu.frontdesk.userGrid({
//						title : "测试"
//					});
//			new Ext.Viewport({
//						layout : 'border',
//						items : [{
//									region : 'center',
//									items : [v],
//									layout : 'fit'
//								}]
//					});
//		});