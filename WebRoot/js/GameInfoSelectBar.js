//显示游戏层
function showlist(obj){
	var arr=new Array();
	arr[0]="search_select_box";
	arr[1]="search_select_qu";
	arr[2]="search_select_fu";
	arr[3]="yxfl";
	for(var i=0;i<arr.length;i++){
		if(arr[i]==obj){
			var selectbox = document.getElementById(obj).style.display;
			if( selectbox == 'none'){
				if(obj=="yxfl"){
					close_nav("DivShim");
				}else{
					show_nav("DivShim");
				}
				show_nav(obj);
			}else{
				close_nav(obj);
				close_nav("DivShim");
			}
		}else{
			close_nav(arr[i]);
			close_nav("DivShim");
		}
	}
}


function show_nav_game(obj){
	var DivRef = document.getElementById(obj);//获得大层--游戏/分区/服务器
	var IfrRef = document.getElementById('DivShim');//获得iframe
	var gameListContent=document.getElementById('gameListContent');//获得游戏数据存放层
	initGameListAction('search_nav', 'GameProvider', 'gamelist');
	
	IfrRef.style.height=DivRef.clientHeight;
	showlist(obj);
}

function initGameListAction(search_nav, GameProvider, gamelist){
	var search_nav = document.getElementById(search_nav);
	if(search_nav == null){
		alertDialog('没有加载游戏列表');
		return;
	}
	var menu_num1=search_nav.getElementsByTagName("a");
	
	//把游戏厂商对应的游戏列表加到列表中
	var menu_num2=document.getElementById(GameProvider).getElementsByTagName('a');
	var i=0;
	var menu_num=new Array();
	for (; i < menu_num1.length; i++) {
		menu_num[i]=menu_num1[i];
	}
	for(var j=0; j<menu_num2.length; j++){
		menu_num[i+j]=menu_num2[j];
	}
	
	var menu_1_Length=menu_num1.length;
	//显示当前列表
	function close_all(num){
		for(i=0; i<menu_num.length; i++){
			document.getElementById(gamelist+i).style.display=i==num?"block":"none";
			if(i < menu_1_Length){
				menu_num[i].className=i==num?"search_nav1":"search_nav2";
			} else {
				menu_num[i].className=i==num?"search_nav2":"search_nav1";
			}
		}
	}
	close_all(0);
	//按钮控制
	for(i=0; i<menu_num.length; i++){
		menu_num[i].a=i;
		menu_num[i].onclick=function(){
			close_all(this.a);
			var obj1=document.getElementById('DivShim');
			var obj2=document.getElementById('search_select_box');
			if(obj1!=null && obj2!=null){obj1.style.height= obj2.clientHeight;}
		}
	}
}

//显示弹出层
function show_nav(obj){
	document.getElementById(obj).style.display="block";
}
//关闭弹出层
function close_nav(obj){
	document.getElementById(obj).style.display="none";
}
