Ext.namespace("Ctgu.frontdesk");
/*
 * Ctgu.frontdesk.RoomDataView = function(){
 * Ctgu.frontdesk.RoomDataView.superclass.constructor.call(this); }
 */
Ctgu.frontdesk.RoomDataView = Ext.extend(Ext.DataView, {
			initComponent : function() {
				Ext.apply(this, {
							tpl : new Ext.XTemplate('<tpl for=".">', '<div class="ux-status-view-wrap">',
									'<div  class="ux-status-view-roompic"><img  ext:qtip="类型:{roomType} 价格:{roomPPrice} 折扣:{roomHPrice}" src={roomImage}></div>',
									'<div class="ux-status-view-roomid" style="color:{roomInfoStateAsColor}">{roomName}</div>', '</div></tpl>')

							,
							autoHeight : true,
							itemSelector : 'div.ux-status-view-wrap',
							singleSelect : true // 初始化时自动触发selectsection时间，为BUG
							,
							overClass : 'x-view-over',
							store : new Ext.data.JsonStore({
										url : 'RoomDataServelet',
										root : 'roomList',
										autoLoad : true,
										fields : [{
													name : "roomID",
													type : "int"
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
												}, "roomImage"
										,"roomInfoStateAsColor"
										]
									})
						});
				Ctgu.frontdesk.RoomDataView.superclass.initComponent.apply(this, arguments);
				this.roomViewMenu = new Ext.menu.Menu({
							id : 'roomViewMenu',
							items : [
									{
										text : '登记该房间',
										id : 'rmCheckinBtn',
										iconCls : 'icon-checkin'
									}, {
										text : '查看登记信息',
										id : 'checkinInfoBtn',
										iconCls : 'icon-checkininfo'
									}, {
										text:'结算退房',
										id:'balanceOutBtn'
									},
									{
										text : '更改房间状态',
										iconCls : 'icon-switch',
										menu : {
											items : [{
														text : '置为空闲房',
														cls : 'no-icon-menu',
														id : 'setVacancy'
													}
													, {
														text : '置为清洁房',
														cls : 'no-icon-menu',
														id : 'setClean'
													}, {
														text : '置为锁房',
														cls : 'no-icon-menu',
														id : 'setBlock'
													}]
										}
									}]
						});
				this.rmCheckinBtn = Ext.getCmp('rmCheckinBtn');
				this.rmCheckinBtn.on({
							'click' : this.onRoomDblClick,
							scope : this
						});
				this.checkinInfoBtn = Ext.getCmp('checkinInfoBtn');
				this.checkinInfoBtn.on({
							'click' : this.showCheckinInfo,
							scope : this
						});
				this.balanceOutBtn=Ext.getCmp('balanceOutBtn');
				this.balanceOutBtn.on({
					'click':this.balanceOut,scope:this
				});
				this.setVacancy = Ext.getCmp('setVacancy');
				this.setVacancy.on({
							'click' : this.onSetVacancyClick,
							scope : this
						});
				this.setClean = Ext.getCmp('setClean');
				this.setClean.on({
							'click' : this.onSetCleanClick,
							scope : this
						});
				this.setBlock = Ext.getCmp('setBlock');
				this.setBlock.on({
							'click' : this.onSetBlockClick,
							scope : this
						});
						
				//this.checkinForm = Ext.getCmp('checkinForm');

				this.on({
							'dblclick' : this.onRoomDblClick,
							scope : this
						});
				this.on({
							'loadexception' : this.onRoomLoadException,
							scope : this
						});
				this.store.on({
							'load' : this.onRoomDataLoad // 可以在dataload时对dataview进行select(0),选中操作
							,
							scope : this,
							buffer : 100
						});
				this.addEvents({
							'dblclickroom' : true,
							'showcheckininfo' : true
						}, {
							'reservCheckin' : true
						});
				/*
				 * this.on({'contextmenu':this.onContextMenu //需要在父节点进行
				 * ,scope:this});
				 */
			},
			onRender : function() {
				Ctgu.frontdesk.RoomDataView.superclass.onRender.apply(this, arguments);
			},
			afterRender : function() {
				Ctgu.frontdesk.RoomDataView.superclass.afterRender.apply(this, arguments);
			},
			/*
			 * ,onContextMenu:function(dview,index,node,e){
			 * console.log('onContextMenu'); e.stopEvent();
			 * this.select(node,false,false); //var coords=e.getXY();
			 * //this.roomViewMenu.showAt([coords[0],coords[1]]); }
			 */

			onRoomDblClick : function() {
				var selNode = this.getSelectedNodes();
				if (selNode && selNode.length > 0) {
					selNode = selNode[0];
					var selRecord = this.getRecord(selNode);
				}
				if (selRecord.get('roomState') == 'F') {
					this.fireEvent('dblclickroom', selRecord);// 触发事件传递给父结点处理
				}else {
					Ext.msg.alert('提示', '该房间暂时不能登记，请检查更新房间状态!');
				}

			},
//			addRoom : function(btn) {
//				if (btn == 'yes') {
//					var selNode = this.getSelectedNodes();
//					if (selNode && selNode.length > 0) {
//						selNode = selNode[0];
//						var selRecord = this.getRecord(selNode);
//					}
//					this.fireEvent('dblclickroom', selRecord);// 触发事件传递给父结点处理
//					var boundEl = Ext.get(selNode);
//					// boundEl.dom.lastChild.style='color:#6E7544';
//					console.log(boundEl.dom.lastChild.firstChild);
//					// boundEl.dom.lastChild.firstChild.nodeValue='登记中...';
//					/*
//					 * selRecord.set('roomInfoStateAsColor','#6E7544');
//					 * selRecord.set('rmId','登记中...');
//					 */
//				}
//			},
			onRoomLoadException : function() {

			},
			onRoomDataLoad : function() {

			},
			balanceOut : function() {
				var selNode = this.getSelectedNodes();
				if (selNode && selNode.length > 0) {
					selNode = selNode[0];
					var selRecord = this.getRecord(selNode);
				}
//				this.checkinForm = Ext.getCmp('checkinForm');
//				this.checkinForm.reservCheckin(selRecord.get('rmId'));
				this.fireEvent('balanceOut',selRecord);
				//alert(selRecord.get('roomID'))
			},
			onSetBlockClick : function() {
				this.updateRoom('L', '#E80033');
			},
			onSetCleanClick : function() {
				this.updateRoom('C', '#00AF4D');
			},
			onSetVacancyClick : function() {
				this.updateRoom('F', '#008CD2');
			},
			
			showCheckinInfo : function() {// 查看登记信息
				var selNode = this.getSelectedNodes();
				if (selNode && selNode.length > 0) {
					selNode = selNode[0];
					var selRecord = this.getRecord(selNode);
				}
				this.fireEvent('showcheckininfo', selRecord.get('roomID'));
			},
//			balanceOut:function(){
//				alert("hhh")
//			},
			updateRoom : function(state, color) {
				//预定房置空房要使预定失效，待处理*
				var selNode = this.getSelectedNodes();
				if (selNode && selNode.length > 0) {
					selNode = selNode[0];
					var selRecord = this.getRecord(selNode);
				}
				selRecord.set('roomState', state);
				selRecord.set('roomInfoStateAsColor', color);
//				rmRecordJSON = Ext.util.JSON.encode(selRecord.data);
				Ext.Ajax.request({
							url : 'RoomInfoServelet',
							params : {
								cmd:"updateRoomState",
								roomID:selRecord.get('roomID'),
								roomState:state
							},
							method : 'POST',
							success : function() {
								Ext.Msg.alert('提示', '房态更改成功!');
							},
							failure : function() {
								Ext.Msg.alert('提示', '房态修改失败，请重新尝试!');
								//this.store.reload();
								var dataview=Ext.getCmp("roomDataView");//刷新主页面房态视图
								dataview.store.reload();
							}
						});
			}
		});
Ext.reg('roomdataview', Ctgu.frontdesk.RoomDataView);
//Ext.onReady(function(){
//	var k= new Ctgu.frontdesk.RoomDataView();
//	var p=new Ext.Panel({
//		title:'房间状态',
//		width:400,
//		height:300,
//		item:[k]
//	});
//	var win=new Ext.Window({
//		title:"测试",
//		
//		items:[p]
//	});
//	win.show();
//});