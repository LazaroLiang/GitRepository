Ext.namespace("Ctgu");
Ext.namespace("Ctgu.frontdesk");
Ctgu.frontdesk.guestGrid = function() {
	Ctgu.frontdesk.guestGrid.superclass.constructor.call(this);
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
var addGuest = function() {
	var btnsubmitclick = function() {
		guestPanel.getForm().submit({
					waitTitle : "请稍后",
					waitMsg : "正在保存客户数据，请稍后……",
					url : 'GuestInfoServelet',
					method : "POST",
					params : {
						cmd : "save"
					},
					success : function(form, action) {
						Ext.Msg.alert("提示", "数据保存成功！");
						// Ext.getCmp("useWin").close();
						userWin.close();
						guestStore.load();

					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "保存数据失败！");
					}
				});
	}
	var btnresetclick = function() {
		guestPanel.getForm().reset();
	}
	var radiogroup = new Ext.form.RadioGroup({
				fieldLabel : '性别',
				width : 100,
				items : [{
							name : 'guestSex',
							inputValue : true,
							boxLabel : '男',
							checked : true
						}, {
							name : 'guestSex',
							inputValue : false,
							boxLabel : '女'
						}]
			});
	// 获取单选组的值
	radiogroup.on('change', function(rdgroup, checked) {
				// alert(checked.getRawValue());
			});
	var guestPanel = new Ext.form.FormPanel({
				id : 'guestPanel',
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
										items : [ // 从上往下的布局
										{
													name : "guestName",
													fieldLabel : "姓名",
													xtype : "textfield"
												}]
									}, {
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										items : [radiogroup]
									}]
						}, {
							layout : 'column',
							border : false,
							items : [{
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										items : [{
													name : "guestAge",
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
										items : [{
													name : "guestCardID",
													fieldLabel : "身份证号",
//													maxLength : 18,
//													minLength : 18,
//													maxLengthText : '身份证号长度应为18位',
//													minLengthText : '身份证号长度应为18位',
													xtype : "textfield",
													vtype : "cardIDCheck"
												}]
									}]
						}, {
							layout : 'column',
							border : false,
							items : [{
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										items : [{
													xtype : 'combo',
													fieldLabel : '客户类型',
													name : 'guestType',
													emptyText : '请选择',
													mode : 'local',
													store : new Ext.data.SimpleStore({
																fields : ['value', 'text'],
																data : [['P', '普通客户'], ['H', '会员'], ['V', 'VIP']]
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
										items : [{
													name : "guestTel",
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
										items : [{
													name : "guestAddress",
													fieldLabel : "地址",
													xtype : "textfield"
												}]
									}, {
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										items : [{
													name : "guestZip",
													fieldLabel : "邮编",
													xtype : "numberfield",
													maxLength : 6,
													minLength : 6,
													maxLengthText : '邮政编码长度应为6位',
													minLengthText : '邮政编码长度应为6位'
												}]
									}]
						}, {
							name : "guestBirthday",
							fieldLabel : "生日",
							xtype : "datefield",
							format : "Y-m-d",
							anchor : '50%'
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
				title : '增加客户信息',
				items : [guestPanel],
				width : 600,
				height : 210,
				id : "useWin"
			});
	userWin.show();
	// Ext.Msg.alert("哈哈");
};
var editGuest = function() {
	// Ext.Msg.alert("哈哈");
	var grid = Ext.getCmp("guestGrid");
	var selectionModel = grid.getSelectionModel();
	if (selectionModel.getCount() == 0) {
		Ext.Msg.alert("提示", "您还未选择需要编辑的记录，请先选择一条记录！");
	} else {
		var btnsubmitclick = function() {
			guestPanel.getForm().submit({
						waitTitle : "请稍后",
						waitMsg : "正在保存客户数据，请稍后……",
						url : 'GuestInfoServelet',
						method : "POST",
						params : {
							cmd : "edit",
							guestID : record.get('guestID')
						},
						success : function(form, action) {
							Ext.Msg.alert("提示", "数据保存成功！");
							// Ext.getCmp("useWin").close();
							userWin.close();
							guestStore.load();

						},
						failure : function(form, action) {
							Ext.Msg.alert("提示", "保存数据失败！");
						}
					});
		}
		var btnresetclick = function() {
			guestPanel.getForm().reset();
		}
		var radiogroup = new Ext.form.RadioGroup({
					fieldLabel : '性别',
					width : 100,
					items : [{
								name : 'guestSex',
								inputValue : true,
								boxLabel : '男',
								checked : true
							}, {
								name : 'guestSex',
								inputValue : false,
								boxLabel : '女'
							}]
				});
		// 获取单选组的值
		radiogroup.on('change', function(rdgroup, checked) {
					// alert(checked.getRawValue());
				});
		var guestPanel = new Ext.form.FormPanel({
					id : 'guestPanel',
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
											items : [ // 从上往下的布局
											{
														name : "guestName",
														fieldLabel : "姓名",
														xtype : "textfield"
													}]
										}, {
											columnWidth : .5, // 该列有整行中所占百分比
											layout : "form",
											border : false,
											items : [radiogroup]
										}]
							}, {
								layout : 'column',
								border : false,
								items : [{
											columnWidth : .5, // 该列有整行中所占百分比
											layout : "form",
											border : false,
											items : [{
														name : "guestAge",
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
											items : [{
														name : "guestCardID",
														fieldLabel : "身份证号",
//														maxLength : 18,
//														minLength : 18,
//														maxLengthText : '身份证号长度应为18位',
//														minLengthText : '身份证号长度应为18位',
														xtype : "textfield",
														vtype : "cardIDCheck"
													}]
										}]
							}, {
								layout : 'column',
								border : false,
								items : [{
											columnWidth : .5, // 该列有整行中所占百分比
											layout : "form",
											border : false,
											items : [{
														xtype : 'combo',
														fieldLabel : '客户类型',
														name : 'guestType',
														emptyText : '请选择',
														mode : 'local',
														store : new Ext.data.SimpleStore({
																	fields : ['value', 'text'],
																	data : [['P', '普通客户'], ['H', '会员'], ['V', 'VIP']]
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
											items : [{
														name : "guestTel",
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
											items : [{
														name : "guestAddress",
														fieldLabel : "地址",
														xtype : "textfield"
													}]
										}, {
											columnWidth : .5, // 该列有整行中所占百分比
											layout : "form",
											border : false,
											items : [{
														name : "guestZip",
														fieldLabel : "邮编",
														xtype : "numberfield",
														maxLength : 6,
														minLength : 6,
														maxLengthText : '邮政编码长度应为11位',
														minLengthText : '邮政编码长度应为11位'
													}]
										}]
							}, {
								name : "guestBirthday",
								fieldLabel : "生日",
								xtype : "datefield",
								// format : "Y年m月d日",
								format : "Y-m-d",
								anchor : '50%'
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
					title : '修改客户信息',
					items : [guestPanel],
					width : 600,
					height : 210,
					id : "useWin"
				});
		userWin.show();

		var record = grid.getSelectionModel().getSelected();
		// alert(record.get("guestBirthday"));
		userWin.getComponent(0).getForm().loadRecord(record);
	}
};
var deleteGuest = function() {
	var grid = Ext.getCmp("guestGrid");
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
									waitMsg : "正在删除客户数据，请稍后……",
									url : 'GuestInfoServelet',
									method : "POST",
									params : {
										cmd : "delete",
										guestID : record.get('guestID')
									},
									success : function(form, action) {
										Ext.Msg.alert("提示", "数据删除成功！");
										// Ext.getCmp("useWin").close();
										// userWin.close();
										guestStore.load();

									},
									failure : function(form, action) {
										Ext.Msg.alert("提示", "数据删除失败！");
									}
								});
					}
				})
	}
};
var guestStore = new Ext.data.JsonStore({
			root : "guestList",
			url : "GuestDataServelet",
			idProperty : "guestId",
			totalProperty : "rowCount",
			fields : [{
						name : 'guestID',
						type : 'int'
					}, "guestName", {
						name : "guestSex"
						// type : "boolean"
				}	, {
						name : "guestAge",
						type : 'int'
					}, "guestCardID", "guestType", "guestTel", "guestAddress", "guestZip", "guestBirthday"]
		});
var toolbar = new Ext.Toolbar([{
			text : "新增客户",
			handler : addGuest
		}, '-', {
			text : "修改客户信息",
			handler : editGuest
		}, '-', {
			text : "删除客户",
			handler : deleteGuest
		}]);

Ctgu.frontdesk.guestGrid = Ext.extend(Ext.grid.GridPanel, {
			id : 'guestGrid',
			// height:120
			// ,layout:'column'
			title : "客户管理",
			store : guestStore,
			tbar : toolbar,
			initComponent : function() {
				Ext.apply(this, {
							columns : [{
										header : "客户ID",
										dataIndex : "guestID",
										width : 60
									}, {
										header : "客户姓名",
										dataIndex : "guestName",
										sortable : true,
										width : 100
									}, {
										header : "客户性别",
										dataIndex : "guestSex",
										width : 60
									}, {
										header : "客户年龄",
										dataIndex : "guestAge",
										width : 60
									}, {
										header : "身份证号",
										dataIndex : "guestCardID",
										width : 150
									}, {
										header : "客户类型",
										dataIndex : "guestType",
										width : 60
									}, {
										header : "联系方式",
										dataIndex : "guestTel",
										width : 100
									}, {
										header : "住址",
										dataIndex : "guestAddress",
										width : 150
									}, {
										header : "邮编",
										dataIndex : "guestZip",
										width : 100
									}, {
										header : "生日",
										dataIndex : "guestBirthday",
										width : 100
									}],
							bbar : new Ext.PagingToolbar({
										store : guestStore,
										pageSize : 5
									})
						});
				guestStore.load();
				Ctgu.frontdesk.guestGrid.superclass.initComponent.apply(this, arguments);
			},
			onRender : function() {
				Ctgu.frontdesk.guestGrid.superclass.onRender.apply(this, arguments);
			},
			afterRender : function() {
				Ctgu.frontdesk.guestGrid.superclass.afterRender.apply(this, arguments);
			}
		});
Ext.reg('guestGrid', Ctgu.frontdesk.guestGrid);
// Ext.onReady(function(){
// var v=new Ctgu.frontdesk.guestGrid({
// title:"测试"
// });
// new Ext.Viewport({
// layout:'border',
// items:[{region:'center',items:[v],layout:'fit'}]
// });
//});