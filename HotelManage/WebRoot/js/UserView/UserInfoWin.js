Ext.namespace("Ctgu");
Ext.namespace("Ctgu.frontdesk");
//Ctgu.frontdesk.userInfoPanel = function() {
//	Ctgu.frontdesk.userInfoPanel.superclass.constructor.call(this);
//}
var btnsubmitclick = function() {
	var formValue = Ext.getCmp("userInfoPanel").getForm().getValues();
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
				},
				failure : function(form, action) {
					Ext.Msg.alert("提示", "保存数据失败！");
				}
			});
};
var btnresetclick = function() {
	Ext.getCmp("userInfoPanel").getForm().reset();
};

var radiogroup = new Ext.form.RadioGroup({
			fieldLabel : '性别',
			width : 100,
			items : [{
						name : 'userSex',
						inputValue : '1',
						boxLabel : '男',
						checked : true
					}, {
						name : 'userSex',
						inputValue : '0',
						boxLabel : '女'
					}]
		});
// 获取单选组的值
radiogroup.on('change', function(rdgroup, checked) {
			// alert(checked.getRawValue());
		});

Ctgu.frontdesk.userInfoPanel = Ext.extend(Ext.form.FormPanel, {
//var formPanel = new Ext.form.FormPanel({
			id : 'userInfoPanel',
			// height:120
			// ,layout:'column'
			border : false,
			labelWidth : 100,
			labelAlign : "right",
			buttonAlign : "center",
			 initComponent : function() {
			 Ext.apply(this, {
			items : [{
						name : "loginName",
						fieldLabel : "登陆名",
						xtype : "textfield"
						
					}, {
						name : "userName",
						fieldLabel : "用户姓名",
						xtype : "textfield"
					}, {
						name : "userAge",
						fieldLabel : "年龄",
						maxValue : 130,
						minValue : 0,
						xtype : "numberfield",
						maxText : "年龄应小于130岁",
						minText : "年龄应大于0岁"
					}, radiogroup, {
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
					}
//						,{
//						xtype:"hide",
//						name:"methodFlag",
//						id:"flag"
//					}
					],
			buttons : [{
						text : "保存",
						handler : btnsubmitclick,
						scope:this
					}, {
						text : "重置",
						handler : btnresetclick,
						scope:this
					}]
		});
		Ctgu.frontdesk.userInfoPanel.superclass.initComponent.apply(this,
				arguments);
	},
	onRender : function() {
		Ctgu.frontdesk.userInfoPanel.superclass.onRender.apply(this, arguments);
	},
	afterRender : function() {
		Ctgu.frontdesk.userInfoPanel.superclass.afterRender.apply(this,
				arguments);
	},
	beforeDestroy : function() {
		Ext.Panel.superclass.beforeDestroy.call(this);
	}
})

Ext.reg('userInfoPanel', Ctgu.frontdesk.userInfoPanel)

//Ext.onReady(function() {
//			Ext.QuickTips.init();
//			var panel = new Ctgu.frontdesk.userInfoPanel;
//			var win=new Ext.Window({
//				title : '用户信息修改',
//				items : [panel],
//				width : 400,
//				height : 230
//			});
//			win.show();
//		})