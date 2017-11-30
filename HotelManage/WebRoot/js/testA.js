Ext.onReady(function () {
            //树的节点数据源
            var node = {
                text: '根',
                expanded: true,
                leaf: false,
                children: [
                    { text: '根下节点一[user图标]', leaf: true, iconCls: 'nodeicon' },
                    { text: '根下节点二', leaf: true },
                    { text: '根下节点三', leaf: false, children: [
                        { text: '节点三子节点一', leaf: true },
                        { text: '节点三子节点二', leaf: false, expanded: true, children: [
                            { text: '节点三子节点二节点一', leaf: true },
                            { text: '节点三子节点二节点二', leaf: true }
                        ]
                        }
                    ]
                    }
                ]
            };
            //树面板(本地数据源)
            var treelocal = new Ext.tree.TreePanel({
                title: 'TreePanelLocal',
                //rootVisible: false,
                root: node
            });
/*            //树面板(服务器数据源)
            var treeservice = new Ext.tree.TreePanel({
                title: 'TreePanelService',
                root: { text: '根', expanded: true },
                //rootVisible: false,
                loader: new Ext.tree.TreeLoader({
                    url: '/App_Ashx/Demo/Tree.ashx'
                })
            });*/
            //单表
            var form = new Ext.form.FormPanel({
                frame: true,
                title: '表单标题',
                style: 'margin:10px',
                items: [treelocal],
                buttons: [{
                    text: '获取选中项',
                    handler: function () {
                        selectNode = treelocal.getSelectionModel().getSelectedNode();
                        alert('TreePanelLocal：' + (selectNode == null ? treelocal.root.text : selectNode.text));
                    }
                }]
            });
            //窗体
            var win = new Ext.Window({
                title: '窗口',
                width: 476,
                height: 374,
                resizable: true,
                modal: true,
                closable: true,
                maximizable: true,
                minimizable: true,
                items: form
            });
            win.show();
        });
/*Ext.onReady(function(){  
        var tree = new Ext.tree.TreePanel({  
            region: 'center',  
            //True表示为面板是可收缩的，并自动渲染一个展开/收缩的轮换按钮在头部工具条  
            collapsible: true,  
            title: '标题',//标题文本  
            width: 200,  
            border : false,//表框  
            autoScroll: true,//自动滚动条  
            animate : true,//动画效果  
            rootVisible: true,//根节点是否可见  
            split: true,  
            tbar:[{  
                text:'展开',  
                handler:function(){  
                        tree.expandAll();  
                }  
            },'-',{  
                text:'折叠',  
                handler:function(){  
                    tree.collapseAll();  
                    tree.root.expand();  
                }  
            }],  
            listeners: {  
                click: function(node) {  
                    //得到node的text属性  
                    Ext.Msg.alert('消息', '你点击了: "' + node.attributes.text+"'");  
                }  
            }  
        });  
        var root = new Ext.tree.TreeNode({text:'我是根'});  
        var root_node1 = new Ext.tree.TreeNode({text:'我是根的1枝'});  
        var root_node2 = new Ext.tree.TreeNode({text:'我是根的2枝'});  
        //插入节点为该节点的最后一个子节点  
        root.appendChild(root_node1);  
        root.appendChild(root_node2);  
        //设置根节点  
        tree.setRootNode(root);  
        var vv = new Ext.Viewport(
        	{ 
        	items:{
	        	title:"表头",
	        	region:"west",
	        	width:200,
	            items: [tree]  
            }
        });  
    });  */