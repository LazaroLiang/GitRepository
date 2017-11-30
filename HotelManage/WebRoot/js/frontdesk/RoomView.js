﻿Ext.namespace("Ctgu");
Ext.namespace("Ctgu.frontdesk");
var clock = new Ext.Toolbar.TextItem('');
Ctgu.frontdesk.RoomView = Ext.extend(Ext.Panel,{
	region:'center'
	,title:'房间状态'
	,autoscroll:true
	,tools:[
				{
					id:'refresh'
					,scope:this
					,handler: function(event, toolEl, panel){
					// refresh logic
						//this.roomDataView.refresh();
						panel.roomDataView.store.reload();
						Ext.ux.Toast.msg('提示','房态已刷新');
					}
				}
			]
	,initComponent: function(){
		Ext.apply(this,{
			tbar:[{
                    	text: '过滤:'
                    },{
                    	xtype: 'textfield',
                    	id: 'filter',
                    	selectOnFocus: true,
                    	width: 100,
                    	listeners: {
                    		'render': {fn:function(){
						    	Ext.getCmp('filter').getEl().on('keyup', function(){
						    		this.filter();
						    	}, this, {buffer:500});
                    		}, scope:this}
                    	}
                    }, ' ', '-', {
                    	text: '过滤条件:'
                    }, {
                    	id: 'filterSelect',
                    	xtype: 'combo',
				        typeAhead: true,
				        triggerAction: 'all',
				        width: 100,
				        editable: false,
				        mode: 'local',
				        displayField: 'desc',
				        valueField: 'name',
				        lazyInit: false,
				        value: '房间号',
				        store: new Ext.data.SimpleStore({
					        fields: ['name', 'desc'],
					        data : [['roomId', '房间号'],['roomState', '房间状态']]
					    }),
					    listeners: {
							//'select': {fn:this.sortImages, scope:this}
					    }
				    }
				/*,'->'
				,{
					text:'房间过滤'
					,iconCls:'icon-roomfilter'
					,menu:{
						items:[
							{
								text:'类型'
								,iconCls:'icon-roomcatalog'
								,menu:{
									items:[
										{
											text:'标准间'
											,checked:true
											,id:'filtStandRm'
										}
										,{
											text:'单人间'
											,checked:true
											,id:'filtSingleRm'
										}
										,{
											text:'三人间'
											,checked:true
											,id:'filtThreeRm'
										}
										,{
											text:'豪华套间'
											,checked:true
											,id:'filtDeluxRm'
										}
									]
								}
							}
							,{
								text:'楼层'
								,iconCls:'icon-roomfloor'
								,menu:{
									items:[
										{
											text:'一楼'
											,checked:true
											,id:'filtFirst'
										}
										,{
											text:'二楼'
											,checked:true
											,id:'filtSecond'
										}
										,{
											text:'三楼'
											,checked:true
											,id:'filtThird'
										}
										,{
											text:'四楼'
											,checked:true
											,id:'filtFourth'
										}
									]
								}
							}
						]
					}
				}*/
			]
			,items:[
				{
					xtype:'roomdataview'
					//,layout:"border"
					,id:'roomDataView'
				}
			]		
			,bbar: 
//			new Ext.ux.StatusBar({
//	            id: 'basic-statusbar'
//		        ,items: 
		        ['->','<font color="#008CD2">空闲房(F)</font>', '-'
		        	,'<font color="#FF7D00">已租房(R)</font>', '-'
		        	,'<font color="#00AF4D">清洁房(C)</font>', '-'
		        	,'<font color="#E80033">锁房(L)</font>', '-'
		        	//,new Date().format('Y-m-d'), '-'
		        //,clock
		        ]
//    		})
		});
		
		/*this.filtStandRm.on({'check':this.onFilt
					,scope:this});
		this.filtSingleRm.on({'check':this.onFilt
					,scope:this});
		this.filtThreeRm.on({'check':this.onFilt
					,scope:this});
		this.filtDeluxRm.on({'check':this.onFilt
					,scope:this});
		this.filtFirst.on({'check':this.onFilt
					,scope:this});
		this.filtSecond.on({'check':this.onFilt
					,scope:this});
		this.filtThird.on({'check':this.onFilt
					,scope:this});
		this.filtFourth.on({'check':this.onFilt
					,scope:this});*/
		//Ctgu.frontdesk.RoomView.superclass.initComponent.call(this);
		Ctgu.frontdesk.RoomView.superclass.initComponent.apply(this,arguments);
		this.roomDataView=this.items.itemAt(0);
		this.roomDataView.on({'dblclickroom':this.onDblClickRoom
			,scope:this});
		this.roomDataView.on({'showcheckininfo':this.onShowCheckinInfo
			,scope:this});
		this.roomDataView.on({'balanceOut':this.balanceOut
			,scope:this});
		this.roomDataView.on({'contextmenu':this.onRoomViewContextMenu
			,scope:this});
		this.addEvents({'dblclickroom':true},{'showcheckininfo':true},{'balanceOut':true});
		//console.log(this.roomDataView.events['dblclickroom']);
	}
	,onRender:function(){
		Ctgu.frontdesk.RoomView.superclass.onRender.apply(this,arguments);
		//这里写Render后的代码
		//Ext.fly(clock.getEl().parentNode).addClass('x-status-text-panel');
//		Ext.TaskMgr.start({
//			run: function(){
//				Ext.fly(clock.getEl()).update(new Date().format('g:i:s A'));
//			},
//			interval: 1000
//		});
	}
	,afterRender:function(){

		Ctgu.frontdesk.RoomView.superclass.afterRender.apply(this,arguments);
	
	}
//	,filtByUser:function(record,id){
//		if(record.get('rmCatalog')=='标准间'){
//			if(this.filtStandRm.getValue=='false'){
//				return false;
//			}else{
//				return true;
//			}
//		}
//		if(record.get('rmCatalog')=='单人间'){
//			if(this.filtSingleRm.getValue=='false'){
//				return false;
//			}else{
//				return true;
//			}
//		}
//		if(record.get('rmCatalog')=='三人间'){
//			if(this.filtThreeRm.getValue=='false'){
//				return false;
//			}else{
//				return true;
//			}
//		}
//		if(record.get('rmCatalog')=='豪华套间'){
//			if(this.filtDeluxRm.getValue=='false'){
//				return false;
//			}else{
//				return true;
//			}
//		}
//		if(record.get('rmCatalog')=='1'){
//			if(this.filtFirst.getValue=='false'){
//				return false;
//			}else{
//				return true;
//			}
//		}
//		if(record.get('rmCatalog')=='2'){
//			if(this.filtSecond.getValue=='false'){
//				return false;
//			}else{
//				return true;
//			}
//		}
//		if(record.get('rmCatalog')=='3'){
//			if(this.filtThird.getValue=='false'){
//				return false;
//			}else{
//				return true;
//			}
//		}
//		if(record.get('filtFourth')=='4'){
//			if(this.filtStandRm.getValue=='false'){
//				return false;
//			}else{
//				return true;
//			}
//		}
//	}
//	,onFit:function(){
//		this.roomDataView.store.filtBy(this.filtByUser);
//		this.roomDataView.select(0);
//	}
	,onRoomViewContextMenu:function(dview,index,node,e){ //房态图右键菜单
		e.stopEvent();
		this.roomDataView.select(node,false,false);
		var coords=e.getXY();
		if(this.roomDataView.getRecord(node).get('roomState')=='F'){	//空闲房时设置相应菜单可见性
			this.roomDataView.roomViewMenu.items.itemAt(0).enable();
			this.roomDataView.roomViewMenu.items.itemAt(1).disable();
			this.roomDataView.roomViewMenu.items.itemAt(2).disable();
			this.roomDataView.roomViewMenu.items.itemAt(3).menu.items.itemAt(0).disable();
			this.roomDataView.roomViewMenu.items.itemAt(3).menu.items.itemAt(1).enable();
			this.roomDataView.roomViewMenu.items.itemAt(3).menu.items.itemAt(2).enable();
		}else if(this.roomDataView.getRecord(node).get('roomState')=='R'){	//已租房设置相应状态			
			this.roomDataView.roomViewMenu.items.itemAt(0).disable();
			this.roomDataView.roomViewMenu.items.itemAt(1).enable();
			this.roomDataView.roomViewMenu.items.itemAt(2).enable();
			this.roomDataView.roomViewMenu.items.itemAt(3).disable();
		}else if(this.roomDataView.getRecord(node).get('roomState')=='C'){//清洁房		
			this.roomDataView.roomViewMenu.items.itemAt(0).disable();
			this.roomDataView.roomViewMenu.items.itemAt(1).disable();
			this.roomDataView.roomViewMenu.items.itemAt(2).disable();
			this.roomDataView.roomViewMenu.items.itemAt(3).enable();
			this.roomDataView.roomViewMenu.items.itemAt(3).menu.items.itemAt(0).enable();
			this.roomDataView.roomViewMenu.items.itemAt(3).menu.items.itemAt(1).disable();
			this.roomDataView.roomViewMenu.items.itemAt(3).menu.items.itemAt(2).enable();
		}else{
			this.roomDataView.roomViewMenu.items.itemAt(0).disable();
			this.roomDataView.roomViewMenu.items.itemAt(1).disable();
			this.roomDataView.roomViewMenu.items.itemAt(2).disable();
			this.roomDataView.roomViewMenu.items.itemAt(3).enable();
			this.roomDataView.roomViewMenu.items.itemAt(3).menu.items.itemAt(0).enable();
			this.roomDataView.roomViewMenu.items.itemAt(3).menu.items.itemAt(1).enable();
			this.roomDataView.roomViewMenu.items.itemAt(3).menu.items.itemAt(2).disable();		
		}
		this.roomDataView.roomViewMenu.showAt([coords[0],coords[1]]);
	}
	,onDblClickRoom:function(selRoomRecord){
		this.fireEvent('dblclickroom',selRoomRecord);
	}
	,balanceOut:function(selRoomRecord){
		//console.log(rmId);
//		this.checkinForm=Ext.getCmp('checkinForm');
//		this.checkinForm.reservCheckin(rmId);
		this.fireEvent('balanceOut',selRoomRecord);
	}
	,onShowCheckinInfo:function(roomId){
		console.info('roomView');
		this.fireEvent('showcheckininfo',roomId);
	}
	,updateRmView:function(){
		this.roomDataView.store.reload();
	}
	,setDataViewStore:function(store){
		this.store = store;
	}
	,filter : function(){
		var filter = Ext.getCmp('filter');
		var comboxValue = Ext.getCmp('filterSelect');
		var filterField = comboxValue.getValue();
		switch(filterField){
		  	case '房间号':
		  		filterField = 'roomName';
		  		break;
		  	case '房间状态':
		  		filterField = 'roomState';
		  		break;
		  	}
		this.roomDataView.store.filter(filterField, filter.getValue());
		this.roomDataView.select(0);
	}
});
Ext.reg('roomview',Ctgu.frontdesk.RoomView);
/*以下为测试*/
//Ext.onReady(function(){
//	var k= new Ctgu.frontdesk.RoomView();
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