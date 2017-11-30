Ext.onReady(function() {
			var guestPanel = new Ext.form.FormPanel({
						id : 'guestPanel',
						border : false,
						labelWidth : 100,
						labelAlign : "right",
						buttonAlign : "center",
						frame : false,
						//applyTo : 'form',
						items : [{
									// xtype : 'panel',
									layout : 'column',
									border : false,
									// defaults : {
									// border : false,
									// layout : "form",
									// columnWidth : .5
									// },
									items : [{
												columnWidth : .5, // 该列有整行中所占百分比
												layout : "form",
												border:false,
												items : [ // 从上往下的布局
												{
															name : "guestName",
															fieldLabel : "姓名",
															xtype : "textfield"
														}]
											}, {
												columnWidth : .5, // 该列有整行中所占百分比
												layout : "form",
												border:false,
												items : [{
															name : "guestAge",
															fieldLabel : "年龄",
															maxValue : 130,
															minValue : 0,
															xtype : "numberfield",
															maxText : "年龄应小于130岁",
															minText : "年龄应大于0岁"
														}]
											}]
								}, {
									name : "guestCardID",
									fieldLabel : "身份证号",
									maxLength : 18,
									minLength : 18,
									maxLengthText : '身份证号长度应为18位',
									minLengthText : '身份证号长度应为18位',
									xtype : "textfield"
								},
								// {
								// name : "guestType",
								// fieldLabel : "客户类型",
								// xtype : "combobox",
								// store:guestTypeStore,
								// displayField:'ChineseType',
								// valueField:'post'
								// },
								{
									name : "guestTel",
									fieldLabel : "联系方式",
									xtype : "numberfield",
									maxLength : 11,
									minLength : 11,
									maxLengthText : '电话号码长度应为11位',
									minLengthText : '电话号码长度应为11位'
								}, {
									name : "guestAddress",
									fieldLabel : "地址",
									xtype : "textfield"
								}, {
									name : "guestZip",
									fieldLabel : "邮编",
									xtype : "numberfield",
									maxLength : 6,
									minLength : 6,
									maxLengthText : '邮政编码长度应为11位',
									minLengthText : '邮政编码长度应为11位'
								}, {
									name : "guestBirthday",
									fieldLabel : "生日",
									xtype : "datefield",
									format : "Y年m月d日"
								}],
						buttons : [{
									text : "保存",
									// handler : btnsubmitclick,
									scope : this
								}, {
									text : "重置",
									// handler : btnresetclick,
									scope : this
								}]
					});
			var userWin = new Ext.Window({
						title : '用户信息修改',
						items : [guestPanel],
						width : 600,
						height : 230,
						id : "useWin"
					});
			userWin.show();
		});