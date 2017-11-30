Ext.namespace("Ctgu");
Ext.namespace("Ctgu.frontdesk");
Ctgu.frontdesk.orderGrid = function() {
	Ctgu.frontdesk.orderGrid.superclass.constructor.call(this);
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
var addOrder = function() {
	var btnsubmitclick = function() {
		orderPanel.getForm().submit({
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
						orderStore.load();

					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "保存数据失败！");
					}
				});
	}
	var btnresetclick = function() {
		orderPanel.getForm().reset();
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
	var orderPanel = new Ext.form.FormPanel({
				id : 'orderPanel',
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
													// maxLength : 18,
													// minLength : 18,
													// maxLengthText :
													// '身份证号长度应为18位',
													// minLengthText :
													// '身份证号长度应为18位',
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
				items : [orderPanel],
				width : 600,
				height : 210,
				id : "useWin"
			});
	userWin.show();
	// Ext.Msg.alert("哈哈");
};
var editOrder = function() {
	// Ext.Msg.alert("哈哈");
	var grid = Ext.getCmp("orderGrid");
	var selectionModel = grid.getSelectionModel();
	if (selectionModel.getCount() == 0) {
		Ext.Msg.alert("提示", "您还未选择需要编辑的记录，请先选择一条记录！");
	} else {
		var record = grid.getSelectionModel().getSelected();
		if (record.get("orderState") == "已结算") {
			Ext.Msg.alert("提示", "你选择的记录已结算，不允许修改！")
		} else {
			var btnsubmitclick = function() {
				orderPanel.getForm().submit({
							waitTitle : "请稍后",
							waitMsg : "正在保存客户数据，请稍后……",
							url : 'OrderInfoServelet',
							method : "POST",
							params : {
								cmd : "edit",
								orderID : record.get('orderID')
							},
							success : function(form, action) {
								Ext.Msg.alert("提示", "数据保存成功！");
								// Ext.getCmp("useWin").close();
								userWin.close();
								orderStore.load();

							},
							failure : function(form, action) {
								Ext.Msg.alert("提示", "保存数据失败！");
							}
						});
			}
			var btnresetclick = function() {
				orderPanel.getForm().reset();
			}
			var orderPanel = new Ext.form.FormPanel({
						id : 'orderPanel',
						border : false,
						labelWidth : 100,
						labelAlign : "right",
						buttonAlign : "center",
						frame : false,
						items : [{
									layout : 'column',
									labelWidth : 60,
									border : false,
									items : [{
												columnWidth : .5, // 该列有整行中所占百分比
												layout : "form",
												border : false,
												items : [ // 从上往下的布局
												{
															name : "orderID",
															fieldLabel : "订单ID",
															xtype : "textfield",
															anchor : '50%',
															disabled : true
														}]
											}, {
												columnWidth : .5, // 该列有整行中所占百分比
												layout : "form",
												border : false,
												items : [{
															name : "guestID",
															fieldLabel : "客户ID",
															xtype : "textfield",
															anchor : '50%',
															disabled : true
														}]
											}]
								}, {
									layout : 'column',
									labelWidth : 60,
									border : false,
									items : [{
												columnWidth : .5, // 该列有整行中所占百分比
												layout : "form",
												border : false,
												items : [{
															name : "roomID",
															fieldLabel : "客房ID",
															xtype : "textfield",
															anchor : '50%',
															disabled : true
														}]
											}, {
												columnWidth : .5, // 该列有整行中所占百分比
												layout : "form",
												border : false,
												items : [{
															xtype : 'datefield',
															name : 'inTime',
															fieldLabel : '入住时间',
															format : 'Y-m-d H:m:s',
															// value : new
															// Date().format('Y-m-d
															// h:m:s'),
															anchor : '50%',
															allowBlank : false,
															readOnly : true,
															disabled : true
														}]
											}]
								}, {
									layout : 'column',
									border : false,
									labelWidth : 60,
									items : [{
												columnWidth : .5, // 该列有整行中所占百分比
												layout : "form",
												border : false,
												items : [{
															xtype : 'datefield',
															name : 'outTime',
															fieldLabel : '退房时间',
															format : 'Y-m-d H:m:s',
															// value : new
															// Date().format('Y-m-d
															// h:m:s'),
															anchor : '50%',
															allowBlank : false
														}]
											}, {
												columnWidth : .5, // 该列有整行中所占百分比
												layout : "form",
												border : false,
												items : [{
															xtype : 'combo',
															fieldLabel : '订单类型',
															name : 'orderType',
															emptyText : '请选择',
															mode : 'local',
															store : new Ext.data.SimpleStore({
																		fields : ['value', 'text'],
																		data : [['true', '钟点房'], ['false', '非钟点房']]
																	}),
															editable : false,
															triggerAction : 'all',
															valueField : 'value',
															displayField : 'text',
															anchor : '50%'
														}]
											}]
								}, {
									layout : 'column',
									labelWidth : 60,
									border : false,
									items : [{
												columnWidth : .5, // 该列有整行中所占百分比
												layout : "form",
												border : false,
												items : [{
													name : "orderMoney",
													fieldLabel : "订       金",
													value : 0,
													xtype : "numberfield",
													anchor : '50%'
														// isabled : true
													}]
											}, {
												columnWidth : .5, // 该列有整行中所占百分比
												layout : "form",
												border : false,
												items : [{
															fieldLabel : '订单状态',
															xtype : 'textfield',
															name : 'orderState',
															value : '未结算' // 待读取
															,
															allowBlank : false,
															readOnly : true,
															disabled : true,
															anchor : '50%'
														}]
											}]
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
						items : [orderPanel],
						width : 850,
						height : 190,
						id : "useWin"
					});
			userWin.show();

			// alert(record.get("inTime"));
			userWin.getComponent(0).getForm().loadRecord(record);
		}
	}
};
var deleteOrder = function() {
	var grid = Ext.getCmp("orderGrid");
	//
	var selectionModel = grid.getSelectionModel();
	if (selectionModel.getCount() == 0) {
		Ext.Msg.alert("提示", "您还未选择需要删除的记录，请先选择一条记录！");
	} else {
		var record = grid.getSelectionModel().getSelected();
		if (record.get("orderState") == "未结算") {
			Ext.Msg.alert("提示", "你选择的记录未结算，不允许删除！")
		} else {
			Ext.Msg.confirm("提示", "数据删除后无法恢复，是否确认删除？", function(btn) {
						if (btn == "yes") {
							var record = grid.getSelectionModel().getSelected();
							Ext.Ajax.request({
										waitTitle : "请稍后",
										waitMsg : "正在删除客户数据，请稍后……",
										url : 'OrderInfoServelet',
										method : "POST",
										params : {
											cmd : "delete",
											orderID : record.get('orderID')
										},
										success : function(form, action) {
											Ext.Msg.alert("提示", "数据删除成功！");
											// Ext.getCmp("useWin").close();
											// userWin.close();
											orderStore.load();

										},
										failure : function(form, action) {
											Ext.Msg.alert("提示", "数据删除失败！");
										}
									});
						}
					})
		}
	}
};
var orderStore = new Ext.data.JsonStore({
			root : "orderList",
			url : "OrderDataServelet",
			idProperty : "orderId",
			totalProperty : "rowCount",
			fields : [{
						name : 'orderID',
						type : 'int'
					}, {
						name : 'guestID',
						type : 'int'
					}, {
						name : 'roomID',
						type : 'int'
					}, "guestName", "inTime", "outTime", "orderState", "orderType", {
						name : "orderMoney",
						type : "float"
					}]
		});
var toolbar = new Ext.Toolbar([{
			text : "新增订单",
			handler : addOrder,
			disabled : true
		}, '-', {
			text : "修改订单信息",
			handler : editOrder
		}, '-', {
			text : "删除订单",
			handler : deleteOrder
		}]);

Ctgu.frontdesk.orderGrid = Ext.extend(Ext.grid.GridPanel, {
			id : 'orderGrid',
			// height:120
			// ,layout:'column'
			title : "客户管理",
			store : orderStore,
			tbar : toolbar,
			initComponent : function() {
				Ext.apply(this, {
							columns : [{
										header : "订单ID",
										dataIndex : "orderID",
										width : 60
									}, {
										header : "客户ID",
										dataIndex : "guestID",
										width : 60
									}, {
										header : "客房ID",
										dataIndex : "roomID",
										width : 60
									}, {
										header : "客户姓名",
										dataIndex : "guestName",
										sortable : true,
										width : 80
									}, {
										header : "入住时间",
										dataIndex : "inTime",
										width : 150
									}, {
										header : "退房时间",
										dataIndex : "outTime",
										width : 150
									}, {
										header : "订单类型",
										dataIndex : "orderType",
										width : 80
									}, {
										header : "订单状态",
										dataIndex : "orderState",
										width : 60
									}, {
										header : "定金",
										dataIndex : "orderMoney",
										width : 60
									}],
							bbar : new Ext.PagingToolbar({
										store : orderStore,
										pageSize : 5
									})
						});
				orderStore.load();
				Ctgu.frontdesk.orderGrid.superclass.initComponent.apply(this, arguments);
			},
			onRender : function() {
				Ctgu.frontdesk.orderGrid.superclass.onRender.apply(this, arguments);
			},
			afterRender : function() {
				Ctgu.frontdesk.orderGrid.superclass.afterRender.apply(this, arguments);
			}
		});
Ext.reg('orderGrid', Ctgu.frontdesk.orderGrid);
//Ext.onReady(function() {
//			var v = new Ctgu.frontdesk.orderGrid({
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