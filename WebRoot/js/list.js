function gridView(tableID, trClassName1, trClassName2, trClassName3){
	var Ptr=document.getElementById(tableID).getElementsByTagName("tr");
	for (i=0;i<Ptr.length;i++) {
		var className=(i%2>0)?trClassName1:trClassName2;
        Object.prototype.oldClassName;
		Ptr[i].className=Ptr[i].oldClassName=className;
		Ptr[i].onmouseover=function(){this.className=trClassName3;};
		Ptr[i].onmouseout=function(){this.className=this.oldClassName;};
	}
}
