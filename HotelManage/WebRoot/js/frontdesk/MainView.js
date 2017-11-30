Ext.namespace("Ctgu");
Ext.namespace("Ctgu.frontdesk");

Ctgu.frontdesk.Mainview = function(){
	Ctgu.frontdesk.Mainview.superclass.constructor.call(this);
}

Ctgu.frontdesk.Mainview = Ext.extend(Ext.Panel,{
		/*Default*/
		title:'MainView'
		,layout:'border'
		//,border:true
		//,height:400
		,initComponent: function(){
			//var roomView = new Ctgu.frontdesk.RoomView();
			/*var checkInForm=new Ctgu.frontdesk.CheckInForm({
				//autoscroll:true
			});*/
			//var guestDetailForm=new Ctgu.frontdesk.GuestDetailForm();
			Ext.apply (this,{
				/*tbar:[
					'-'
					,{
						text:'登记'
						,iconCls:'icon-checkin'
						,minWidth:'50'
					}
					,'-'
					,{
						text:'预定'
						,iconCls:'icon-reserv'
					}
					,'-'
				]*/
				//layout:'border'
				items:[
					{
						xtype:'roomview'
					}			
				]
			});
			Ctgu.frontdesk.Mainview.superclass.initComponent.apply(this,arguments);
			this.roomView=this.items.itemAt(0);
//			this.roomView.filtStandRm=Ext.getCmp('filtStandRm');
//			this.roomView.filtSingleRm=Ext.getCmp('filtSingleRm');
//			this.roomView.filtThreeRm=Ext.getCmp('filtThreeRm');
//			this.roomView.filtDeluxRm=Ext.getCmp('filtDeluxRm');
//			this.roomView.filtFirst=Ext.getCmp('filtFirst');
//			this.roomView.filtSecond=Ext.getCmp('filtSecond');
//			this.roomView.filtThird=Ext.getCmp('filtThird');
//			this.roomView.filtFourth=Ext.getCmp('filtFourth');

			//this.checkInForm=this.items.itemAt(1).items.itemAt(0).items.itemAt(0);
			//this.guestDetailForm=this.items.itemAt(1).items.itemAt(0).items.itemAt(1);
			this.roomView.on({'dblclickroom':this.onDblClickRoom
				,scope:this});
			this.roomView.on({'showcheckininfo':this.onShowCheckinInfo
				,scope:this});
			this.roomView.on({'balanceOut':this.balanceOut
				,scope:this});
			/*this.roomView.on({'reservCheckin':this.onReservCheckin
				,scope:this});*/
//			this.checkInForm.on({'updateRmView':this.onUpdateRmView
//				,scope:this});
//			this.checkInForm.setGuestDetailForm('detailForm'+this.id);
		}
		,loadMask:function(){
			this.loadMask = new Ext.LoadMask(this,({msg:'loading',msgCls:'x-mask-loading'}));
			this.loadMask.show();
		}
		,hideMask:function(){
			this.loadMask.hide();
		}
		,onRender:function(){
			//this.getEl.mask.call(this,'loading');
			Ctgu.frontdesk.Mainview.superclass.onRender.apply(this,arguments);
		}
		,onDblClickRoom: function(selRoomRecord){//登记房间
			var win=new Ext.Window({
				title:"登记入住",
				id:"winMain",
				layout:"fit",
				width:900,
				height:400,
				items:[new Ctgu.frontdesk.CheckInForm()]
			});
			win.show();
			var checkInfrom=Ext.getCmp('checkInForm');	//将房间信息传递至panel页面
			checkInfrom.getSelRoomRecord(selRoomRecord);
		}
		,balanceOut:function(selRoomRecord){
			//alert(selRoomRecord.get("roomID")+" "+selRoomRecord.get("roomPPrice"));
			//console.log(this.checkInForm);
			//this.checkInForm.reservCheckin(rmId);
			var win=new Ext.Window({
				title:"结算退房",
				id:"winMain",
				layout:"fit",
				width:900,
				height:400,
				items:[new Ctgu.frontdesk.BalanceForm()]
			});
			win.show();
			var balanceform=Ext.getCmp('balanceForm');	//将房间信息传递至panel页面
			balanceform.getSelRoomRecord(selRoomRecord);
			balanceform.form.load({
				waitMsg : "正在加载数据请稍后……",
				waitTitle : "提示",
				url : "MainInfoServelet",
				params : {
					cmd : "loadPartOrderInfo",
					roomID : selRoomRecord.get("roomID")
				},
				method : "POST",
				success : function(form, action) {
					Ext.Msg.alert("提示", "加载数据成功！");
					balanceform.CalcuMoney();
				},
				failure : function(form, action) {
					Ext.Msg.alert("提示", "加载数据失败！");
				},
				scope : this
			})
			
		}
		,onShowCheckinInfo:function(rmId){
			//this.checkInForm.showCheckinInfo(rmId);
			var win=new Ext.Window({
				title:"登记信息",
				id:"winMain",
				layout:"fit",
				region:"center",
				width:900,
				height:350,
				items:[new Ctgu.frontdesk.CheckInForm()]
			});
			win.show();
			var formPanel=Ext.getCmp('checkInForm');
			
			var checkinBtn=Ext.getCmp('checkinBtn');	//隐藏按钮
			var checkinResetBtn=Ext.getCmp('checkinResetBtn');
			checkinBtn.setVisible(false);
			checkinResetBtn.setVisible(false);
			formPanel.form.load({
				waitMsg : "正在加载数据请稍后……",
				waitTitle : "提示",
				url : "MainInfoServelet",
				params : {
					cmd : "loadOrderInfo",
					roomID : rmId
				},
				method : "POST",
				success : function(form, action) {
					Ext.Msg.alert("提示", "加载数据成功！");
					formPanel.getForm().items.eachKey(function(key,item){    
  						item.el.dom.readOnly=true;   
					})
					// userForm.isAdd=true;
				},
				failure : function(form, action) {
					Ext.Msg.alert("提示", "加载数据失败！");
				},
				scope : this
			})
		}
		,onUpdateRmView:function(){
			this.roomView.updateRmView();
		}
		
});
Ext.reg('mainview',Ctgu.frontdesk.Mainview);