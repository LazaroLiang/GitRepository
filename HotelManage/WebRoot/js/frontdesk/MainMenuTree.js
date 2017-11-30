Ext.namespace("Ctgu");
Ext.namespace("Ctgu.frontdesk");
/*
 * Neo.frontdesk.MainMenuTree = function(){
 * Neo.frontdesk.MainMenuTree.superclass.constructor.call(this); }
 */
var node = {
	text : '酒店管理系统',
	iconCls : 'icon-hotel',
	id : 'gen',
	expanded : true,
	leaf : false,
	children : [{
				text : '订房管理',
				leaf : false,
				children : [{
							text : '客房管理',
							id : 'roomManage',
							leaf : true
						}, {
							text : '客户管理',
							id : 'guestManage',
							leaf : true
						}, {
							text : "订单管理",
							id : 'orderManage',
							leaf : true
						}
						, {
							text : "结算管理",
							id : 'balanceManage',
							leaf : true
						}
						]
			}, {
				text : "订餐管理(拓展模块)",
				leaf : false,
				children : [{
							text : "订餐中心",
							id : 'foodCenter',
							leaf : true
						}]

			}, {
				text : "用户管理（管理员权限）",
				leaf : true,
				id : "userManage"
			}]
};
Ctgu.frontdesk.MainMenuTree = Ext.extend(Ext.tree.TreePanel, {
			/* Default */
			id : 'mainMenu',
			title : '主菜单',
			region : 'west',
			minSize : 100,
			maxSize : 300,
			width : 180,
			border : true,
			rootVisible : true,
			lines : false,
			autoScroll : true,
			root : node,
			collapseFirst : false,
			initComponent : function() {
				Ext.apply(this, {

				});
				Ctgu.frontdesk.MainMenuTree.superclass.initComponent.apply(this, arguments);

			},
			onRender : function() {
				Ctgu.frontdesk.MainMenuTree.superclass.onRender.apply(this, arguments);
			}
		});
Ext.reg('mainmenutree', Ctgu.frontdesk.MainMenuTree);