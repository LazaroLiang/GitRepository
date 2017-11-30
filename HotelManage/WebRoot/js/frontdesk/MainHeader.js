Ext.namespace("Ctgu");
Ext.namespace("Ctgu.frontdesk");
Ctgu.frontdesk.MainHeader = function() {
	Ctgu.frontdesk.MainHeader.superclass.constructor.call(this);
}
var btnsubmitclick = function() { // 表单提交函数
	//var formValue = Ext.getCmp("userInfoPanel").getForm().getValues();
	var form = Ext.getCmp("userInfoPanel").getForm();
	form.submit({
				waitTitle : "请稍后",
				waitMsg : "正在保存数据，请稍后……",
				url : 'LoadUserInfoServelet',
				method : "POST",
				params : {
					cmd : "update",
					userID : idse
				},
				success : function(form, action) {
					Ext.Msg.alert("提示", "数据保存成功！");
					Ext.getCmp("useWin").close();
				},
				failure : function(form, action) {
					Ext.Msg.alert("提示", "保存数据失败！");
				}
			});
};
var btnresetclick = function() {
	Ext.getCmp("userInfoPanel").getForm().reset();
};
updateUserInfo = function() {
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
	var formPanel = new Ext.form.FormPanel({
				id : 'userInfoPanel',
				border : false,
				labelWidth : 100,
				labelAlign : "right",
				buttonAlign : "center",
				items : [{
							name : "loginName",
							id:"loginName",
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
													if(response.responseText == "true"){
														Ext.Msg.alert("提示","此用户名已被占用，请使用其它用户名！");
													}else{
													}
												},
												failure:function(response,opts){
													Ext.Msg.alert("提示","检查用户名是否可用时出现异常，请联系管理员！");
												}
											});
								}
							}
						}, {
							name : "userName",
							fieldLabel : "用户姓名",
							xtype : "textfield"
						}, radiogroup, {
							name : "userAge",
							fieldLabel : "年龄",
							maxValue : 130,
							minValue : 0,
							xtype : "numberfield",
							maxText : "年龄应小于130岁",
							minText : "年龄应大于0岁"
						}, {
							name : "userTel",
							fieldLabel : "联系方式",
							xtype : "numberfield",
							maxLength : 11,
							minLength : 11,
							maxLengthText : '电话号码长度应为11位',
							minLengthText : '电话号码长度应为11位'
						}, {
							name : "userAddress",
							fieldLabel : "地址",
							xtype : "textfield"
						}],
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
				title : '用户信息修改',
				items : [formPanel],
				width : 400,
				height : 230,
				id : "useWin"
			});
	userWin.show();
	formPanel.form.load({
				waitMsg : "正在加载数据请稍后……",
				waitTitle : "提示",
				url : "LoadUserInfoServelet",
				params : {
					cmd : "query",
					userID : idse
				},
				method : "POST",
				success : function(form, action) {
					Ext.Msg.alert("提示", "加载数据成功！");
					// userForm.isAdd=true;
				},
				failure : function(form, action) {
					Ext.Msg.alert("提示", "加载数据失败！");
				},
				scope : this
			})
	// userForm.isAdd=true;
	// userWin.on('beforeclose',function(){
	// Ext.Msg.confirm("提示","你确定要退出窗口吗?",function(btn){
	// if(btn=='yes'){
	// alert("退出了");
	// }else{
	// alert("还没");
	// }
	// },this);
	//
	// })
}
Ctgu.frontdesk.MainHeader = Ext.extend(Ext.Panel, {
			id : 'headerPanel',
			height : 90,
			layout : 'column',
			border : false,
			// font:20,
			initComponent : function() {

				Ext.apply(this, {
							items : [{
										columnWidth : .55,
										border : false,
										layout : 'fit',
										items : [{
											xtype : 'panel',
											id : 'logo-header',
											border : false,
											html : '<img src="image/header.png">'
												//html:"<center><h2><font color='red'>欢迎您使用三峡大学酒店管理系统</font></h2></center>"
											}]
									}, {
										columnWidth : .45,
										border : false,
										layout : 'fit',

										items : [{
											xtype : 'panel',
											id : 'control-header',
											border : false,
											height : 80,
											// font:20,
											tbar : ['欢迎', {
														text : namese, // [编辑个人资料]
														// color:"red"
														handler : updateUserInfo,
														scope : this
													}, '登陆系统,单击姓名编辑个人资料', {
														xtype : 'tbspacer',
														width : 10
													}, '-', {
														xtype : 'tbspacer',
														width : 10
													}, {
														text : '修改密码',
														// iconCls : 'icon-system',
														handler : function() {
															// var flag=false;
															// 自定义验证方式
															Ext.apply(Ext.form.VTypes, {
																		password : function(val, field) {
																			// return flag;
																			if (field.flag == true) {
																				return true;
																			} else {
																				return false;
																			}
																		},
																		passwordText : "原密码输入错误,请重新输入！",

																		passwordCompare : function(val, field) {// val指这里的文本框值，field指这个文本框组件，大家要明白这个意思
																			if (field.confirmTo) {// confirmTo是我们自定义的配置参数，一般用来保存另外的组件的id值
																				var pwd = Ext.get(field.confirmTo);// 取得confirmTo的那个id的值
																				return (val == pwd.getValue());
																			}
																			return true;
																		},
																		passwordCompareText : "与你第一次输入的密码不一致，请重新输入！"

																	});
															var panel = new Ext.form.FormPanel({
																		labelAlign : 'right',
																		labelWidth : 60,
																		buttonAlign : 'center',
																		region : 'center',
																		bodyStyle : 'padding:6px 0px 0px 15px',
																		items : [{
																					name : "oldPassword",
																					xtype : "textfield",
																					fieldLabel : "原密码",
																					inputType : "password",
																					allowBlank : false,
																					blankText : "原密码不允许为空",
																					maxLength : 6,
																					minLength : 6,
																					maxLengthText : '密码长度应为6位',
																					minLengthText : '密码长度应为6位',
																					id : "oldPassword",
																					// vtype:"password",
																					flag : true,
																					listeners : { // 添加光标离开事件
																						blur : function() {
																							// lert("离开了");
																							// Ajax请求查看原密码是否正确
																							if (Ext.getCmp('oldPassword').getValue() != "") {
																								Ext.Ajax.request({
																											url : 'LoadUserInfoServelet',
																											params : {
																												userID : idse,
																												cmd : "checkPassWord",
																												oldPassword : Ext.getCmp('oldPassword').getValue()
																											},
																											method : "POST",
																											success : function(response, opts) {
																												if (response.responseText == "true") {
																													// Ext.Msg.alert("提示",response.responseText);

																													// Ext.Msg.alert("提示",Ext.getCmp('oldPassword').flag)
																													Ext.getCmp('oldPassword').flag = true;

																												} else {
																													Ext.getCmp('oldPassword').setValue("");
																													Ext.getCmp('oldPassword').focus();
																													Ext.Msg.alert("提示", "原密码错误，请重新输入！");
																													Ext.getCmp('oldPassword').focus();
																													Ext.getCmp('oldPassword').flag = false;

																												}

																											},
																											failure : function(resp, opts) {// 请求失败
																												var respText = Ext.util.JSON.decode(resp.responseText);
																												Ext.Msg.alert('错误', respText.error);
																												//
																												// password:{flag};
																											}

																										});

																							}
																						}
																					}
																				}, {
																					name : "newPasswordOne",
																					xtype : "textfield",
																					fieldLabel : "新密码",
																					inputType : "password",
																					allowBlank : false,
																					blankText : "密码不允许为空",
																					maxLength : 6,
																					minLength : 6,
																					maxLengthText : '密码长度应为6位',
																					minLengthText : '密码长度应为6位',
																					id : "pass1"
																				}, {
																					name : "newPasswordTwo",
																					xtype : "textfield",
																					fieldLabel : "确认密码",
																					inputType : "password",
																					confirmTo : "pass1",
																					vtype : "passwordCompare",
																					allowBlank : false,
																					blankText : "密码不允许为空",
																					maxLength : 6,
																					minLength : 6,
																					maxLengthText : '密码长度应为6位',
																					minLengthText : '密码长度应为6位',
																					id : "pass2"
																				}],
																		buttons : [{
																					text : "提交",
																					handler : function() {
																						panel.getForm().submit({
																									waitTitle : "请稍后",
																									waitMsg : "正在修改密码，请稍后……",
																									url : 'LoadUserInfoServelet',
																									params : {
																										userID : idse,
																										// password:newPasswordOne,
																										cmd : "updatePassWord"
																									},
																									success : function() {
																										Ext.Msg.alert("提示", "密码修改成功！")
																										win.close();
																									},
																									failure : function() {
																										Ext.Msg.alert("提示", "密码修改失败，请重试或者联系管理员！")
																									}

																								});
																					}
																				}, {
																					text : "重置",
																					handler : function() {
																						panel.getForm().reset();
																					}
																				}]
																	});
															var win = new Ext.Window({
																		title : "修改密码",
																		width : 280,
																		height : 165,
																		items : [panel]
																	});
															win.show();
															Ext.getCmp('oldPassword').focus(false, 100);// 使光标定位到原密码输入框
														}
													}, {
														xtype : 'tbspacer',
														width : 10
													}, '-', {
														xtype : 'tbspacer',
														width : 10
													}, {
														text : "联系我们",
														handler : function() {
															var win = new Ext.Window({
																		title : "联系我们",
																		width : 300,
																		height : 200,
																		html : "<center><h2><font color='red'>欢迎您使用三峡大学酒店管理系统</font></h2></br>"
																				+ "该系统软件所有权归三峡大学计算机与信息学院所有</br></br>" + "你有任何疑问都能与<a href='www.baidu.com'>我</a>联系</br></br>"
																				+ "我的邮箱是：<font color='blue'>1737014281@qq.com</font></br></br>"
																				+ "我的电话号码是：<font color='gree'>18986836140</font></br></br>" + "感谢你的使用，祝您生活愉快！</center>"
																	});
															win.show();
														}
													}, {
														xtype : 'tbspacer',
														width : 10
													}, '-', {
														xtype : 'tbspacer',
														width : 10
													}, {
														text : '注销',
														// iconCls : 'icon-logout',
														handler : function() {
															Ext.Msg.show({
																		title : '注销系统',
																		msg : '提示:注销系统前请注意保存数据,确定要注销吗?',
																		buttons : Ext.Msg.YESNO,
																		fn : function(btn) {
																			if (btn == 'yes') {
																				var path = window.location.pathname;

																				path = path.substring(0, path.lastIndexOf('/'));
																				//path=path.substring(0,path.length-1);
																				path = path.substring(0, path.lastIndexOf('/') + 1);
																				//path += "Login.jsp";
																				window.location = path;
																			}
																		},
																		animEl : 'elId',
																		icon : Ext.MessageBox.QUESTION
																	});
														}
													}]
										}]
									}]
						});
				Ctgu.frontdesk.MainHeader.superclass.initComponent.apply(this, arguments);
			},
			onRender : function() {
				Ctgu.frontdesk.MainHeader.superclass.onRender.apply(this, arguments);
			},
			afterRender : function() {
				Ctgu.frontdesk.MainHeader.superclass.afterRender.apply(this, arguments);
			},
			beforeDestroy : function() {
				Ext.Panel.superclass.beforeDestroy.call(this);
			}

		});
Ext.reg('mainheader', Ctgu.frontdesk.MainHeader)