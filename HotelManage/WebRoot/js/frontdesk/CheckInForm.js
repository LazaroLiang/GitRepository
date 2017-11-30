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
Ctgu.frontdesk.CheckInForm = Ext.extend(Ext.form.FormPanel, {
			//title : '登记信息',
			id:'checkInForm',
			bodyStyle : 'padding:5px 5px 0',
			buttonAlign : "center",
			width : 300,
			frame : true,
			waitMsgTarget : true,
			infoState : 'init',
			initComponent : function() {
				Ext.apply(this, {

							buttons : [{
										id : 'checkinBtn',
										text : '登记'
									}, {
										id : 'checkinResetBtn',
										text : '重置'
									}],
							items : [{
										xtype : 'fieldset',
										autoHeight : true,
										collapsible : true,
										title : '登记信息',
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
																			format : 'Y-m-d h:m:s',
																			value : new Date().format('Y-m-d h:m:s'),
																			anchor : '50%',
																			allowBlank : false
																		}]
															}, {
																columnWidth : .5,
																border : false,
																layout : 'form',
																items : [{
																			xtype : 'datefield',
																			name : 'outTime',
																			fieldLabel : '退房时间',
																			format : 'Y-m-d h:m:s',
																			value : new Date().format('Y-m-d h:m:s'),
																			anchor : '50%',
																			allowBlank : false
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
//																			xtype : 'combo',
//																			fieldLabel : '订单状态',
//																			name : 'orderState',
//																			emptyText : '请选择',
//																			mode : 'local',
//																			store : new Ext.data.SimpleStore({
//																						fields : ['value', 'text'],
//																						data : [['true', '已结算'], ['false', '未结算']]
//																					}),
//																			editable : false,
//																			triggerAction : 'all',
//																			valueField : 'value',
//																			displayField : 'text'
																			fieldLabel:'订单状态'
																			,xtype:'textfield'
																			,name:'orderState'
																			,value:'未结算'	//待读取
																			,allowBlank:false
																			,readOnly:true
																			,disabled:true
																			,anchor:'50%'
																		}]
															}, {
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
																			anchor:'50%'
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
																			name : "orderMoney",
																			fieldLabel : "订       金",
																			value : 0,
																			xtype : "numberfield",
																			anchor : '50%'
																		}]
															}]
												}]
									}, {
										xtype : 'fieldset',
										autoHeight : true,
										collapsible : true,
										title : '客户信息',
										items : [{
													layout : 'column',
													items : [{
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "guestName",
																			fieldLabel : "姓名",
																			xtype : "textfield",
																			anchor : '50%'
																		}]
															}, {
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			fieldLabel : '性别',
																			xtype : 'combo',
																			name : 'guestSex',
																			allowBlank : false,
																			anchor : '50%',
																			displayField : 'cioGuestGender',
																			mode : 'local',
																			editable : false,
																			value : '男',
																			triggerAction : 'all',
																			store : new Ext.data.SimpleStore({
																						fields : ['cioGuestGender'],
																						data : [['男'], ['女']]
																					})
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
																			name : "guestCardID",
																			fieldLabel : "身份证号",
																			xtype : "textfield",
																			vtype : "cardIDCheck",
																			anchor : '50%'
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
																			name : "guestAge",
																			fieldLabel : "年龄",
																			maxValue : 130,
																			minValue : 0,
																			xtype : "numberfield",
																			maxText : "年龄应小于130岁",
																			minText : "年龄应大于0岁",
																			anchor : '50%'
																		}]
															}, {
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "guestTel",
																			fieldLabel : "联系方式",
																			xtype : "numberfield",
																			maxLength : 11,
																			minLength : 11,
																			maxLengthText : '电话号码长度应为11位',
																			minLengthText : '电话号码长度应为11位',
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
																			name : "guestAddress",
																			fieldLabel : "地址",
																			xtype : "textfield",
																			anchor : '50%'
																		}]
															}, {
																columnWidth : .5,
																layout : 'form',
																labelWidth : 60,
																border : false,
																items : [{
																			name : "guestZip",
																			fieldLabel : "邮编",
																			xtype : "numberfield",
																			maxLength : 6,
																			minLength : 6,
																			maxLengthText : '邮政编码长度应为6位',
																			minLengthText : '邮政编码长度应为6位',
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
																			name : "guestBirthday",
																			fieldLabel : "生日",
																			xtype : "datefield",
																			format : "Y-m-d",
																			anchor : '50%'
																		}]
															}]
												}]
									}

							]
						});
				Ctgu.frontdesk.CheckInForm.superclass.initComponent.apply(this, arguments);
				this.checkinBtn=Ext.getCmp('checkinBtn');
				this.checkinResetBtn=Ext.getCmp('checkinResetBtn');
				this.checkinBtn.on({'click':this.onCheckinBtnClick
	    		,scope:this});
	    		this.checkinResetBtn.on({'click':this.oncheckinResetBtn,scope:this})
			},
			onRender : function() {
				Ctgu.frontdesk.CheckInForm.superclass.onRender.apply(this, arguments);
			},
			afterRender : function() {
				Ctgu.frontdesk.CheckInForm.superclass.afterRender.apply(this, arguments);
			},
			onCheckinBtnClick:function(){
				var panel=Ext.getCmp('checkInForm');
				panel.getForm().submit({
						waitTitle : "请稍后",
						waitMsg : "正在保存订单信息，请稍后……",
						url : 'MainInfoServelet',
						method : "POST",
						params : {
							cmd : "saveOrder",
							roomID : record.get('roomID')
						},
						success : function(form, action) {
							Ext.Msg.alert("提示", "登记成功！");
							var userWin=Ext.getCmp("winMain");	//关闭窗口
							userWin.close();
							var dataview=Ext.getCmp("roomDataView");//刷新主页面房态视图
							dataview.store.reload();
						},
						failure : function(form, action) {
							Ext.Msg.alert("提示", "保存数据失败！");
						}
					});
			},
			oncheckinResetBtn:function(){
				var panel=Ext.getCmp('checkInForm');
				panel.getForm().reset();
			},
			getSelRoomRecord:function(selRoomRecord){
				record=selRoomRecord;
			}
		});

Ext.reg('checkinform',Ctgu.frontdesk.CheckInForm);
//Ext.onReady(function() {
//			Ext.QuickTips.init();
//			Ext.form.Field.prototype.msgTarget = 'side';
//			 var v = new Ctgu.frontdesk.CheckInForm({
//
//			 });
//			var win=new Ext.Window({
//				layout:"fit",
//				width:900,
//				height:400,
//				items:[v]
//			});
//			win.show();
//		});