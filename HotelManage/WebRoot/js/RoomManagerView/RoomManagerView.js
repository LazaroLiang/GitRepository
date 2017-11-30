Ext.namespace("Ctgu");
Ext.namespace("Ctgu.frontdesk");
Ctgu.frontdesk.roomView = function() {
	Ctgu.frontdesk.roomView.superclass.constructor.call(this);
}
// 自定义验证方式
Ext.apply(Ext.form.VTypes, {
			roomNameCheck : function(val, field) {
				var reg = /(^(\d|S|s|D|d|T|t|B|b)\d{3}$)/;
				if (reg.test(val) == false) {
					return false;
				}else {
					return true;
				}
			},
			roomNameCheckText : "房间名称格式应为S/s/D/d/T/t/B/b+三位数字,请重新输入！"
		});
var addRoom = function() {
	var btnsubmitclick = function() {
		roomPanel.getForm().submit({
					waitTitle : "请稍后",
					waitMsg : "正在保存客户数据，请稍后……",
					url : 'RoomInfoServelet',
					method : "POST",
					params : {
						cmd : "save"
					},
					success : function(form, action) {
						Ext.Msg.alert("提示", "数据保存成功！");
						// Ext.getCmp("useWin").close();
						userWin.close();
						roomStore.load();

					},
					failure : function(form, action) {
						Ext.Msg.alert("提示", "保存数据失败！");
					}
				});
	}
	var btnresetclick = function() {
		roomPanel.getForm().reset();
	}
	var roomPanel = new Ext.FormPanel({
				id : 'roomPanel',
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
											name : "roomName",
											fieldLabel : "客房名称",
											xtype : "textfield",
											vtype : "roomNameCheck"
											}]
									}, {
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										items : [{
													xtype : 'combo',
													fieldLabel : '客房类型',
													name : 'roomType',
													emptyText : '请选择',
													mode : 'local',
													store : new Ext.data.SimpleStore({
																fields : ['value', 'text'],
																data : [['S', '单人间'], ['D', '标准间'], ['T', '三人间'], ['B', '大床房']]
															}),
													editable : false,
													triggerAction : 'all',
													valueField : 'value',
													displayField : 'text'
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
													fieldLabel : '客房状态',
													name : 'roomState',
													emptyText : '请选择',
													mode : 'local',
													store : new Ext.data.SimpleStore({
																fields : ['value', 'text'],
																data : [['F', '空闲'], ['L', '锁房'], ['R', '已租'],['C', '清洁']]
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
													name : "roomPPrice",
													fieldLabel : "普通价格",
													xtype : "numberfield"
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
													name : "roomHPrice",
													fieldLabel : "会员价格",
													xtype : "numberfield"
												}]
									}, {
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										items : [{
													name : "roomVPrice",
													fieldLabel : "VIP价格",
													xtype : "numberfield"
												}]
									}]
						}, {
							name : "roomHourPrice",
							fieldLabel : "钟点房价格",
							xtype : "numberfield"// ,
							// anchor : '50%'
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
				title : '增加客房信息',
				items : [roomPanel],
				width : 600,
				height : 210,
				id : "useWin"
			});
	userWin.show();
	// Ext.Msg.alert("哈哈");
};
var editRoom = function() {
	// Ext.Msg.alert("哈哈");
	var grid = Ext.getCmp("roomView");
	var selectionModel = grid.getSelectionModel();
	if (selectionModel.getCount() == 0) {
		Ext.Msg.alert("提示", "您还未选择需要编辑的记录，请先选择一条记录！");
	} else {
		var btnsubmitclick = function() {
			roomPanel.getForm().submit({
						waitTitle : "请稍后",
						waitMsg : "正在保存客户数据，请稍后……",
						url : 'RoomInfoServelet',
						method : "POST",
						params : {
							cmd : "edit",
							roomID : record.get('roomID')
						},
						success : function(form, action) {
							Ext.Msg.alert("提示", "数据保存成功！");
							// Ext.getCmp("useWin").close();
							userWin.close();
							roomStore.load();

						},
						failure : function(form, action) {
							Ext.Msg.alert("提示", "保存数据失败！");
						}
					});
		}
		var btnresetclick = function() {
			roomPanel.getForm().reset();
		}
		var roomPanel = new Ext.form.FormPanel({
				id : 'roomPanel',
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
											name : "roomName",
											fieldLabel : "客房名称",
											xtype : "textfield",
											vtype : "roomNameCheck"
											}]
									}, {
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										items : [{
													xtype : 'combo',
													fieldLabel : '客房类型',
													name : 'roomType',
													emptyText : '请选择',
													mode : 'local',
													store : new Ext.data.SimpleStore({
																fields : ['value', 'text'],
																data : [['S', '单人间'], ['D', '标准间'], ['T', '三人间'], ['B', '大床房']]
															}),
													editable : false,
													triggerAction : 'all',
													valueField : 'value',
													displayField : 'text'
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
													fieldLabel : '客房状态',
													name : 'roomState',
													emptyText : '请选择',
													mode : 'local',
													store : new Ext.data.SimpleStore({
																fields : ['value', 'text'],
																data : [['F', '空闲'], ['L', '锁房'], ['R', '已租'],['C', '清洁']]
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
													name : "roomPPrice",
													fieldLabel : "普通价格",
													xtype : "numberfield"
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
													name : "roomHPrice",
													fieldLabel : "会员价格",
													xtype : "numberfield"
												}]
									}, {
										columnWidth : .5, // 该列有整行中所占百分比
										layout : "form",
										border : false,
										items : [{
													name : "roomVPrice",
													fieldLabel : "VIP价格",
													xtype : "numberfield"
												}]
									}]
						}, {
							name : "roomHourPrice",
							fieldLabel : "钟点房价格",
							xtype : "numberfield"// ,
							// anchor : '50%'
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
					title : '修改客房信息',
					items : [roomPanel],
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
var deleteRoom = function() {
	var grid = Ext.getCmp("roomView");
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
									url : 'RoomInfoServelet',
									method : "POST",
									params : {
										cmd : "delete",
										roomID : record.get('roomID')
									},
									success : function(form, action) {
										Ext.Msg.alert("提示", "数据删除成功！");
										// Ext.getCmp("useWin").close();
										// userWin.close();
										roomStore.load();

									},
									failure : function(form, action) {
										Ext.Msg.alert("提示", "数据删除失败！");
									}
								});
					}
				})
	}
};
var roomStore = new Ext.data.JsonStore({
			root : "roomList",
			url : "RoomListDataServelet",
			idProperty : "roomId",
			totalProperty : "rowCount",
			fields : [{
						name : 'roomID',
						type : 'int'
					}, "roomName", "roomType", "roomState", {
						name : "roomPPrice",
						type : "float"
					}, {
						name : "roomHPrice",
						type : "float"
					}, {
						name : "roomVPrice",
						type : "float"
					}, {
						name : "roomHourPrice",
						type : "float"
					}]
		});
var toolbar = new Ext.Toolbar([{
			text : "新增客房",
			handler : addRoom
		}, '-', {
			text : "修改客房信息",
			handler : editRoom
		}, '-', {
			text : "删除客房",
			handler : deleteRoom
		}]);

Ctgu.frontdesk.roomView = Ext.extend(Ext.grid.GridPanel, {
			id : 'roomView',
			title : "客房管理",
			store : roomStore,
			tbar : toolbar,
			initComponent : function() {
				Ext.apply(this, {
							columns : [{
										header : "客房ID",
										dataIndex : "roomID",
										width : 60
									}, {
										header : "客房名称",
										dataIndex : "roomName",
										sortable : true,
										width : 100
									}, {
										header : "客房类型",
										dataIndex : "roomType",
										width : 60
									}, {
										header : "客房状态",
										dataIndex : "roomState",
										width : 60
									}, {
										header : "普通价格",
										dataIndex : "roomPPrice",
										width : 100
									}, {
										header : "会员价格",
										dataIndex : "roomHPrice",
										width : 150
									}, {
										header : "VIP价格",
										dataIndex : "roomVPrice",
										width : 100
									}, {
										header : "钟点房价格/小时",
										dataIndex : "roomHourPrice",
										width : 100
									}],
							bbar : new Ext.PagingToolbar({
										store : roomStore,
										pageSize : 5
									})
						});
				roomStore.load();
				Ctgu.frontdesk.roomView.superclass.initComponent.apply(this, arguments);
			},
			onRender : function() {
				Ctgu.frontdesk.roomView.superclass.onRender.apply(this, arguments);
			},
			afterRender : function() {
				Ctgu.frontdesk.roomView.superclass.afterRender.apply(this, arguments);
			}
		});
Ext.reg('roomGrid', Ctgu.frontdesk.roomView);
//Ext.onReady(function() {
//			Ext.QuickTips.init();
//			// Ext.lib.Ajax.defaultPostHeader += '; charset=utf-8';
//			Ext.form.Field.prototype.msgTarget = 'side';
//			var v = new Ctgu.frontdesk.roomView({
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