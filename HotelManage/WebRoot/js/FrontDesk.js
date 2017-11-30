Ext.onReady(function() {
			Ext.QuickTips.init();
			Ext.lib.Ajax.defaultPostHeader += '; charset=utf-8';
			Ext.form.Field.prototype.msgTarget = 'side';

			// 定义左侧主菜单
			var mainMenu = new Ctgu.frontdesk.MainMenuTree({
						collapsible : true,
						split : true,
						margins : '5 0 5 5',
						cmargins : '5 5 5 5'
					});
			var maintab = function() {
				maintab.superclass.constructor.call(this, {
							title : 'Tab',
							id : 'tabViewer',
							border : false,
							activeTab : 0,
							tabPosition : 'top'
							// ,plugins: new Ext.ux.TabCloseMenu()
							,
							layoutOnTabChange : true,
							items : [{
								xtype : 'mainview',
								title : "主页面",
								border : false
									// closeable:true
								}]
						});
			};
			Ext.extend(maintab, Ext.TabPanel, {
						loadClass : function(node, name) { // (name,cls)
							var title = node.attributes.text;
							var id = node.attributes.id;
							// console.log(id);
							var tab = Ext.getCmp(id);
							// alert(title);
							if (tab) {
								this.setActiveTab(tab);
							} else {
								if (id == 'guestManage') {
									var p = this.add(new Ctgu.frontdesk.guestGrid({
												title : '客户管理',
												closable : true
											}));
								}
								if (id == 'roomManage') {
									var p = this.add(new Ctgu.frontdesk.roomView({
												title : '客房管理',
												closable : true
											}));
								}
								if (id == 'orderManage') {
									var p = this.add(new Ctgu.frontdesk.orderGrid({
												title : '订单管理',
												closable : true
											}));
								}
								if (id == 'balanceManage') {
									var p = this.add(new Ctgu.frontdesk.balanceGrid({
												title : '结算单管理',
												closable : true
											}));
								}
								if(id=='foodCenter'){
									Ext.Msg.alert("提示","此模块为拓展模块，后期将进行开发完善，敬请期待！");
								}
								if(id=='userManage'){
									if(userType==1){
										var p = this.add(new Ctgu.frontdesk.userGrid({
												title : '用户管理',
												closable : true
											}));
									}else{
										//alert(userType);
										Ext.Msg.alert("提示","系统检测到您为普通用户，您没有操作此模块的权限！");
									}
								}
								
								this.setActiveTab(p);
							}
						}
					});
			var viewport = new Ext.Viewport({
						enableTabScroll : true,
						layout : "border",
						items : [{// title:namese,
							border : false,
							region : 'north',
							xtype : 'mainheader'
						}, mainMenu, {// xtype:"tabpanel",
									border : false,
									region : "center",
									layout:'fit',
									items : [new maintab()]
									///items:[new Ctgu.frontdesk.guestGrid]

								}, {
									title : "三峡大学酒店管理系统",
									border : false,
									region : "south",
									height : 30
								}]

					});
			var leftmenu = viewport.items.itemAt(1);
			var centermain = viewport.items.itemAt(2).items.itemAt(0);
			var name = "test";
			leftmenu.on('click', function(node, e) {
				//if(node.isLeaf()){
				e.stopEvent();
				//Ext.Msg.alert("测试"+centermain.getXType());
				centermain.loadClass(node, name);//取叶子节点属性
					//}
				});
		});