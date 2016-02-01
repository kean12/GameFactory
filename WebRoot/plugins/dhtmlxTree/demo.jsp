<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxtree.css">
<script  src="codebase/dhtmlxcommon.js"></script>
<script  src="codebase/dhtmlxtree.js"></script>
<script  src="codebase/ext/dhtmlxtree_json.js"></script>
 
 
<table>
    <tr>
        <td valign="top">
            <div id="treeboxbox_tree" style="width:250px; height:218px;background-color:#f5f5f5;border :1px solid Silver;"></div>
        </td>
        <td  style="padding-left:25" valign="top">
        Two state checkboxes<br><br>
        <a href="javascript:void(0);" onClick="tree.setCheck(tree.getSelectedItemId(),true);">Check item</a><br><br>
        <a href="javascript:void(0);" onClick="tree.setCheck(tree.getSelectedItemId(),false);">UnCheck item</a><br><br>
        <a href="javascript:void(0);" onClick="tree.setSubChecked(tree.getSelectedItemId(),true);">Check branch</a><br><br>
        <a href="javascript:void(0);" onClick="tree.setSubChecked(tree.getSelectedItemId(),false);">UnCheck branch</a><br><br>
        <a href="javascript:void(0);" onClick="alert(tree.getAllChecked());">Get list of checked</a><br><br>
        </td>
    </tr>
    <tr>
        <td valign="top">
            <div id="treeboxbox_tree2" style="width:250px; height:218px;background-color:#f5f5f5;border :1px solid Silver;; overflow:auto;"></div>
        </td>
        <td  style="padding-left:25" valign="top">
            Three state checkboxes<br>
            <br>
        <a href="javascript:void(0);" onClick="tree2.setCheck(tree2.getSelectedItemId(),true);">Check item</a><br><br>
        <a href="javascript:void(0);" onClick="tree2.setCheck(tree2.getSelectedItemId(),false);">UnCheck item</a><br><br>
        <a href="javascript:void(0);" onClick="alert(tree2.getAllChecked());">Get list of checked</a><br><br>
        </td>
    </tr>
</table>    
<script>tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", 0);
tree.setSkin('dhx_skyblue');
tree.setImagePath("codebase/imgs/csh_bluebooks/");
tree.enableCheckBoxes(1);
tree.loadXML("tree3.xml");
//tree.loadXML("tree_data.jsp");
tree2 = new dhtmlXTreeObject("treeboxbox_tree2", "100%", "100%", 0);
 
tree2.setSkin('dhx_skyblue');
tree2.setImagePath("codebase/imgs/csh_bluebooks/");
tree2.setXMLAutoLoading("data.json");

tree2.enableCheckBoxes(1);
tree2.enableThreeStateCheckboxes(true);
//tree2.loadXML("plugins/dhtmlxTree/codebase/tree3.xml");
tree2.loadJSON("data.json");
</script>