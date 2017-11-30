Ext.namespace("Ctgu");
Ext.namespace("Ctgu.frontdesk");
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
var record;
CalcuTime = function(start, end, type) {

	//alert(end+" "+start);
	var ee=Ext.util.Format.date(end, 'm/d/Y H:m:s');
	var ss=Ext.util.Format.date(start, 'm/d/Y H:m:s');
	//alert(ee+" "+ss);
	var e = new Date(ee);
	var s = new Date(ss);	
	//alert(e+" "+s);
	var result;
	if (type == "true" || type == "钟点房") {
		//alert((e.getTime() - s.getTime()) / (3600000));
		result = Math.round((e.getTime() - s.getTime()) / (3600000));// 计算小时
		if(result>=24){
			Ext.Msg.alert("友情提示","钟点房租住时间超过24小时，建议办理非钟点房结算！");
		}
		//alert("true:"+result);
		//alert(result);
	} else {
		result = Math.round((e.getTime() - s.getTime()) / (86400000));// 计算天
		// alert("false:"+result);
		
	}
	
	return result;
}
CalcuRoomCost = function(orderType, rentTime, guestType) {
	//alert(orderType+" "+guestType);
	var roomCost = 0;
	var panel = Ext.getCmp('balanceForm');
	if (orderType == "true" || orderType == "钟点房") {
		roomCost = rentTime * record.get("roomHourPrice");// 若为钟点房
		panel.getForm().findField('roomPrice').setValue(record.get("roomHourPrice"));
	} else {
		if (guestType == "P" || guestType == "普通客户") {
			roomCost = rentTime * record.get("roomPPrice");
			panel.getForm().findField('roomPrice').setValue(record.get("roomPPrice"));
		} else if (guestType == "H" || guestType == "会员") {
			roomCost = rentTime * record.get("roomHPrice");
			panel.getForm().findField('roomPrice').setValue(record.get("roomHPrice"));
		} else if (guestType == "V" || guestType == "VIP") {
			roomCost = rentTime * record.get("roomVPrice");
			panel.getForm().findField('roomPrice').setValue(record.get("roomVPrice"));
		} else {
			roomCost = -1;
		}
	}
	return roomCost;
}
Ctgu.frontdesk.BalanceForm = Ext.extend(Ext.form.FormPanel, {
			// title : '登记信息',
			id : 'balanceForm',
			bodyStyle : 'padding:5px 5px 0',
			buttonAlign : "center",
			width : 300,
			frame : true,
			waitMsgTarget : true,
			infoState : 'init',
			initComponent : function() {
				Ext.apply(this, {

							buttons : [{
										id : 'beforeBalanceBtn',
										text : '清账'
									},{
										id : 'checkinBtn',
										text : '结算'
									}, {
										id : 'checkinResetBtn',
										text : '重置'
									}],
							items : [{
										xtype : 'fieldset',
										autoHeight : true,
										collapsible : true,
										title : '订单信息',
										items : [{
													layout : 'column',
													// labelAlign : 'top',
													labelWidth : 60,
													items : [{
																columnWidth : .5,
																border : false,
																layout : 'form',
																items : [{
																			xtype : 'datefield',
																			name : 'inTime',
																			fieldLabel : '入住时间',
																			format : 'Y-m-d H:m:s',
																			value : new Date().format('Y-m-d h:m:s'),
																			anchor : '50%',
																			allowBlank : false,
																			readOnly:true,
																			disabled : true
																		}]
															}, {
																columnWidth : .5,
																border : false,
																layout : 'form',
																items : [{
																			xtype : 'datefield',
																			name : 'outTime',
																			fieldLabel : '退房时间',
																			format : 'Y-m-d H:m:s',
																			value : new Date().format('Y-m-d h:m:s'),
																			anchor : '50%',
																			allowBlank : false
//																			listeners : { // 添加光标离开事件
//																				blur : this.CalcuMoney
//
//																			}
																		}]
															}]
												}, {
													layout : 'column',
													// labelAlign : 'top',
													labelWidth : 60,
													items : [{
																columnWidth : .5,
																border : false,
																layout : 'form',
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
															}, {
																columnWidth : .5,
																border : false,
																layout : 'form',
																items : [{
																			name : "orderMoney",
																			fieldLabel : "订       金",
																			value : 0,
																			xtype : "numberfield",
																			anchor : '50%',
																			disabled:true
																		}]
															}]
												}, {
													layout : 'column',
													labelAlign : 'left',
													items : [{
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "rentTime",
																			fieldLabel : "租房时间",
																			value : 0,
																			xtype : "numberfield",
																			anchor : '50%',
																			disabled:true
																		}]
															}, {
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "roomPrice",
																			fieldLabel : "单价",
																			value : 0,
																			xtype : "numberfield",
																			anchor : '50%',
																			disabled:true
																		}]
															}]
												}]
									}, {
										xtype : 'fieldset',
										autoHeight : true,
										collapsible : true,
										title : '结算信息',
										items : [{
													layout : 'column',
													items : [{
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "guestName",
																			fieldLabel : "客户姓名",
																			xtype : "textfield",
																			anchor : '50%',
																			disabled:true
																		}]
															}, {
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
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
																			displayField : 'text',
																			anchor : '50%',
																			disabled:true
																		}]
															}]
												}, {
													layout : 'column',
													items : [{
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "roomCost",
																			fieldLabel : "房费",
																			xtype : "numberfield",
																			// vtype
																			// :
																			// "cardIDCheck",
																			anchor : '50%'
																		}]
															}, {
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "extraCost",
																			fieldLabel : "其它费用",
																			value : 0,
																			xtype : "numberfield",
																			anchor : '50%'
																		}]
															}]
												}, {
													layout : 'column',
													items : [{
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "totalCost",
																			fieldLabel : "总费用",
																			value : 0,
																			xtype : "numberfield",
																			anchor : '50%'
																		}]
															}, {
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "freeCost",
																			fieldLabel : "优惠费用",
																			value : 0,
																			xtype : "numberfield",
																			anchor : '50%'
																		}]
															}]
												}, {
													layout : 'column',
													items : [{
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "dueCost",
																			fieldLabel : "应付费用",
																			value : 0,
																			xtype : "numberfield",
																			anchor : '50%'
																		}]
															}, {
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			xtype : 'datefield',
																			name : 'balanceTime',
																			fieldLabel : '结算时间',
																			format : 'Y-m-d H:m:s',
																			// value
																			// :
																			// new
																			// Date().format('Y-m-d
																			// h:m:s'),
																			anchor : '50%',
																			allowBlank : false,
																			disabled:true

																		}]
															}]
												}, {
													layout : 'column',
													items : [{
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "OperateName",
																			fieldLabel : "操作员",
																			xtype : 'textfield',
																			value : namese // 待读取
																			,
																			allowBlank : false,
																			readOnly : true,
																			disabled : true,
																			anchor : '50%'
																		}]
															}, {
																name : "orderID",
																xtype : "hidden"
															}, {
																name : "roomID",
																xtype : "hidden"
															}, {
																name : "guestID",
																xtype : "hidden"
															}]
												}]
									}

							]
						});
				Ctgu.frontdesk.BalanceForm.superclass.initComponent.apply(this, arguments);
				this.checkinBtn = Ext.getCmp('checkinBtn');
				this.checkinResetBtn = Ext.getCmp('checkinResetBtn');
				this.clearBalance=Ext.getCmp('beforeBalanceBtn');
				this.checkinBtn.on({
							'click' : this.onCheckinBtnClick,
							scope : this
						});
				this.checkinResetBtn.on({
							'click' : this.oncheckinResetBtn,
							scope : this
						});
				this.clearBalance.on({
							'click' : this.CalcuMoney,
							scope : this
						})
			},
			onRender : function() {
				Ctgu.frontdesk.BalanceForm.superclass.onRender.apply(this, arguments);
			},
			afterRender : function() {
				Ctgu.frontdesk.BalanceForm.superclass.afterRender.apply(this, arguments);
			},

			CalcuMoney : function() {
				var panel = Ext.getCmp('balanceForm');
				var startTime = panel.getForm().findField('inTime').getValue();
				var endTime = panel.getForm().findField('outTime').getValue();
				var orderType = panel.getForm().findField('orderType').getValue(); // 订单类型钟点房/非钟点房
				//alert(startTime+" "+endTime+" "+orderType);
				var rentTime = CalcuTime(startTime, endTime, orderType); // 计算租用的时间
				panel.getForm().findField('rentTime').setValue(rentTime);
				// alert(rentTime);

				var guestType = panel.getForm().findField('guestType').getValue(); // 客户类型
				var extraCost = panel.getForm().findField('extraCost').getValue(); // 其它费用
				var freeCost = panel.getForm().findField('freeCost').getValue(); // 优惠金额
				var orderMoney = panel.getForm().findField('orderMoney').getValue(); // 定金
				// alert(guestType);
				// alert(orderType+" "+rentTime+" "+guestType);

				var roomCost = CalcuRoomCost(orderType, rentTime, guestType);// 计算房费
				// alert(roomCost);

				// var roomCost=200;
				panel.getForm().findField('balanceTime').setValue(endTime);

				panel.getForm().findField('roomCost').setValue(roomCost);
				panel.getForm().findField('totalCost').setValue(roomCost + extraCost);
				var totalCost = panel.getForm().findField('totalCost').getValue(); // 总费用
				panel.getForm().findField('dueCost').setValue(totalCost - freeCost - orderMoney);

				// Ext.get('balanceTime').setValue(startTime);
			},
			onCheckinBtnClick : function() {
				Ext.Msg.alert("温馨提醒","费用信息可能发生了变化，请先点击清账按钮核对费用信息！");
				var panel = Ext.getCmp('balanceForm');
				panel.getForm().submit({
							waitTitle : "请稍后",
							waitMsg : "正在保存结算信息，请稍后……",
							url : 'MainInfoServelet',
							method : "POST",
							params : {
								cmd : "saveBalance",
								balanceOperate:idse,
								OperateName:namese
								//roomID : record.get('roomID')
							},
							success : function(form, action) {
								Ext.Msg.alert("提示", "结算成功！");
								var userWin = Ext.getCmp("winMain"); // 关闭窗口
								userWin.close();
								var dataview = Ext.getCmp("roomDataView");// 刷新主页面房态视图
								dataview.store.reload();
							},
							failure : function(form, action) {
								Ext.Msg.alert("提示", "结算失败，请联系管理员！");
							}
						});
			},
			oncheckinResetBtn : function() {
				var panel = Ext.getCmp('checkInForm');
				panel.getForm().reset();
			},
			getSelRoomRecord : function(selRoomRecord) {
				record = selRoomRecord;
			}
		});

Ext.reg('balanceform', Ctgu.frontdesk.BalanceForm);
//Ext.onReady(function() {
//			Ext.QuickTips.init();
//			Ext.form.Field.prototype.msgTarget = 'side';
//			var v = new Ctgu.frontdesk.BalanceForm({
//
//			});
//			var win = new Ext.Window({
//						layout : "fit",
//						width : 900,
//						height : 400,
//						items : [v]
//					});
//			win.show();
//		});