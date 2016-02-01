;(function() {
ImportJavscript = {
url: function(url) {
document.write('<script type="text/javascript" src="' + url + '"></scr' + 'ipt>');
}
};
})();
if(typeof YAHOO=="undefined"||!YAHOO){var YAHOO={}}YAHOO.namespace=function(){var a=arguments,b=null,d,e,c;for(d=0;d<a.length;d=d+1){c=(""+a[d]).split(".");b=YAHOO;for(e=(c[0]=="YAHOO")?1:0;e<c.length;e=e+1){b[c[e]]=b[c[e]]||{};b=b[c[e]]}}return b};YAHOO.log=function(b,a,c){var d=YAHOO.widget.Logger;if(d&&d.log){return d.log(b,a,c)}else{return false}};YAHOO.register=function(d,i,a){var e=YAHOO.env.modules,c,f,g,h,b;if(!e[d]){e[d]={versions:[],builds:[]}}c=e[d];f=a.version;g=a.build;h=YAHOO.env.listeners;c.name=d;c.version=f;c.build=g;c.versions.push(f);c.builds.push(g);c.mainClass=i;for(b=0;b<h.length;b=b+1){h[b](c)}if(i){i.VERSION=f;i.BUILD=g}else{YAHOO.log("mainClass is undefined for module "+d,"warn")}};YAHOO.env=YAHOO.env||{modules:[],listeners:[]};YAHOO.env.getVersion=function(a){return YAHOO.env.modules[a]||null};YAHOO.env.ua=function(){var b={ie:0,opera:0,gecko:0,webkit:0,mobile:null,air:0,caja:0},c=navigator.userAgent,a;if((/KHTML/).test(c)){b.webkit=1}a=c.match(/AppleWebKit\/([^\s]*)/);if(a&&a[1]){b.webkit=parseFloat(a[1]);if(/ Mobile\//.test(c)){b.mobile="Apple"}else{a=c.match(/NokiaN[^\/]*/);if(a){b.mobile=a[0]}}a=c.match(/AdobeAIR\/([^\s]*)/);if(a){b.air=a[0]}}if(!b.webkit){a=c.match(/Opera[\s\/]([^\s]*)/);if(a&&a[1]){b.opera=parseFloat(a[1]);a=c.match(/Opera Mini[^;]*/);if(a){b.mobile=a[0]}}else{a=c.match(/MSIE\s([^;]*)/);if(a&&a[1]){b.ie=parseFloat(a[1])}else{a=c.match(/Gecko\/([^\s]*)/);if(a){b.gecko=1;a=c.match(/rv:([^\s\)]*)/);if(a&&a[1]){b.gecko=parseFloat(a[1])}}}}}a=c.match(/Caja\/([^\s]*)/);if(a&&a[1]){b.caja=parseFloat(a[1])}return b}();(function(){YAHOO.namespace("util","widget","example");if("undefined"!==typeof YAHOO_config){var d=YAHOO_config.listener,a=YAHOO.env.listeners,b=true,c;if(d){for(c=0;c<a.length;c=c+1){if(a[c]==d){b=false;break}}if(b){a.push(d)}}}})();YAHOO.lang=YAHOO.lang||{};(function(){var f=YAHOO.lang,b="[object Array]",e="[object Function]",a=Object.prototype,c=["toString","valueOf"],d={isArray:function(g){return a.toString.apply(g)===b},isBoolean:function(g){return typeof g==="boolean"},isFunction:function(g){return a.toString.apply(g)===e},isNull:function(g){return g===null},isNumber:function(g){return typeof g==="number"&&isFinite(g)},isObject:function(g){return(g&&(typeof g==="object"||f.isFunction(g)))||false},isString:function(g){return typeof g==="string"},isUndefined:function(g){return typeof g==="undefined"},_IEEnumFix:(YAHOO.env.ua.ie)?function(i,j){var k,g,h;for(k=0;k<c.length;k=k+1){g=c[k];h=j[g];if(f.isFunction(h)&&h!=a[g]){i[g]=h}}}:function(){},extend:function(h,g,i){if(!g||!h){throw new Error("extend failed, please check that all dependencies are included.")}var j=function(){},k;j.prototype=g.prototype;h.prototype=new j();h.prototype.constructor=h;h.superclass=g.prototype;if(g.prototype.constructor==a.constructor){g.prototype.constructor=g}if(i){for(k in i){if(f.hasOwnProperty(i,k)){h.prototype[k]=i[k]}}f._IEEnumFix(h.prototype,i)}},augmentObject:function(h,i){if(!i||!h){throw new Error("Absorb failed, verify dependencies.")}var l=arguments,j,g,k=l[2];if(k&&k!==true){for(j=2;j<l.length;j=j+1){h[l[j]]=i[l[j]]}}else{for(g in i){if(k||!(g in h)){h[g]=i[g]}}f._IEEnumFix(h,i)}},augmentProto:function(g,h){if(!h||!g){throw new Error("Augment failed, verify dependencies.")}var j=[g.prototype,h.prototype],i;for(i=2;i<arguments.length;i=i+1){j.push(arguments[i])}f.augmentObject.apply(this,j)},dump:function(o,j){var m,k,h=[],g="{...}",n="f(){...}",i=", ",l=" => ";if(!f.isObject(o)){return o+""}else{if(o instanceof Date||("nodeType" in o&&"tagName" in o)){return o}else{if(f.isFunction(o)){return n}}}j=(f.isNumber(j))?j:3;if(f.isArray(o)){h.push("[");for(m=0,k=o.length;m<k;m=m+1){if(f.isObject(o[m])){h.push((j>0)?f.dump(o[m],j-1):g)}else{h.push(o[m])}h.push(i)}if(h.length>1){h.pop()}h.push("]")}else{h.push("{");for(m in o){if(f.hasOwnProperty(o,m)){h.push(m+l);if(f.isObject(o[m])){h.push((j>0)?f.dump(o[m],j-1):g)}else{h.push(o[m])}h.push(i)}}if(h.length>1){h.pop()}h.push("}")}return h.join("")},substitute:function(g,u,n){var q,r,s,k,j,h,l=[],t,p="dump",m=" ",v="{",i="}",o;for(;;){q=g.lastIndexOf(v);if(q<0){break}r=g.indexOf(i,q);if(q+1>=r){break}t=g.substring(q+1,r);k=t;h=null;s=k.indexOf(m);if(s>-1){h=k.substring(s+1);k=k.substring(0,s)}j=u[k];if(n){j=n(k,j,h)}if(f.isObject(j)){if(f.isArray(j)){j=f.dump(j,parseInt(h,10))}else{h=h||"";o=h.indexOf(p);if(o>-1){h=h.substring(4)}if(j.toString===a.toString||o>-1){j=f.dump(j,parseInt(h,10))}else{j=j.toString()}}}else{if(!f.isString(j)&&!f.isNumber(j)){j="~-"+l.length+"-~";l[l.length]=t}}g=g.substring(0,q)+j+g.substring(r+1)}for(q=l.length-1;q>=0;q=q-1){g=g.replace(new RegExp("~-"+q+"-~"),"{"+l[q]+"}","g")}return g},trim:function(h){try{return h.replace(/^\s+|\s+$/g,"")}catch(g){return h}},merge:function(){var g={},i=arguments,j=i.length,h;for(h=0;h<j;h=h+1){f.augmentObject(g,i[h],true)}return g},later:function(h,n,g,l,k){h=h||0;n=n||{};var m=g,i=l,j,o;if(f.isString(g)){m=n[g]}if(!m){throw new TypeError("method undefined")}if(!f.isArray(i)){i=[l]}j=function(){m.apply(n,i)};o=(k)?setInterval(j,h):setTimeout(j,h);return{interval:k,cancel:function(){if(this.interval){clearInterval(o)}else{clearTimeout(o)}}}},isValue:function(g){return(f.isObject(g)||f.isString(g)||f.isNumber(g)||f.isBoolean(g))}};f.hasOwnProperty=(a.hasOwnProperty)?function(h,g){return h&&h.hasOwnProperty(g)}:function(h,g){return !f.isUndefined(h[g])&&h.constructor.prototype[g]!==h[g]};d.augmentObject(f,d,true);YAHOO.util.Lang=f;f.augment=f.augmentProto;YAHOO.augment=f.augmentProto;YAHOO.extend=f.extend})();YAHOO.register("yahoo",YAHOO,{version:"2.7.0",build:"1799"});(function(){YAHOO.env._id_counter=YAHOO.env._id_counter||0;var ao=YAHOO.util,ai=YAHOO.lang,aE=YAHOO.env.ua,at=YAHOO.lang.trim,aN={},aJ={},ag=/^t(?:able|d|h)$/i,y=/color$/i,aj=window.document,z=aj.documentElement,aM="ownerDocument",aD="defaultView",av="documentElement",ax="compatMode",aP="offsetLeft",ae="offsetTop",aw="offsetParent",x="parentNode",aF="nodeType",aq="tagName",af="scrollLeft",aI="scrollTop",ad="getBoundingClientRect",au="getComputedStyle",aQ="currentStyle",ah="CSS1Compat",aO="BackCompat",aK="class",an="className",ak="",ar=" ",ay="(?:^|\\s)",aG="(?= |$)",Y="g",aB="position",aL="fixed",G="relative",aH="left",aC="top",az="medium",aA="borderLeftWidth",ac="borderTopWidth",ap=aE.opera,al=aE.webkit,am=aE.gecko,aa=aE.ie;ao.Dom={CUSTOM_ATTRIBUTES:(!z.hasAttribute)?{"for":"htmlFor","class":an}:{htmlFor:"for",className:aK},get:function(f){var e,d,c,a,b;if(f){if(f[aF]||f.item){return f}if(typeof f==="string"){e=f;f=aj.getElementById(f);if(f&&f.id===e){return f}else{if(f&&aj.all){f=null;d=aj.all[e];for(a=0,b=d.length;a<b;++a){if(d[a].id===e){return d[a]}}}}return f}if(f.DOM_EVENTS){f=f.get("element")}if("length" in f){c=[];for(a=0,b=f.length;a<b;++a){c[c.length]=ao.Dom.get(f[a])}return c}return f}return null},getComputedStyle:function(a,b){if(window[au]){return a[aM][aD][au](a,null)[b]}else{if(a[aQ]){return ao.Dom.IE_ComputedStyle.get(a,b)}}},getStyle:function(a,b){return ao.Dom.batch(a,ao.Dom._getStyle,b)},_getStyle:function(){if(window[au]){return function(b,d){d=(d==="float")?d="cssFloat":ao.Dom._toCamel(d);var a=b.style[d],c;if(!a){c=b[aM][aD][au](b,null);if(c){a=c[d]}}return a}}else{if(z[aQ]){return function(b,e){var a;switch(e){case"opacity":a=100;try{a=b.filters["DXImageTransform.Microsoft.Alpha"].opacity}catch(d){try{a=b.filters("alpha").opacity}catch(c){}}return a/100;case"float":e="styleFloat";default:e=ao.Dom._toCamel(e);a=b[aQ]?b[aQ][e]:null;return(b.style[e]||a)}}}}}(),setStyle:function(b,c,a){ao.Dom.batch(b,ao.Dom._setStyle,{prop:c,val:a})},_setStyle:function(){if(aa){return function(c,b){var a=ao.Dom._toCamel(b.prop),d=b.val;if(c){switch(a){case"opacity":if(ai.isString(c.style.filter)){c.style.filter="alpha(opacity="+d*100+")";if(!c[aQ]||!c[aQ].hasLayout){c.style.zoom=1}}break;case"float":a="styleFloat";default:c.style[a]=d}}else{}}}else{return function(c,b){var a=ao.Dom._toCamel(b.prop),d=b.val;if(c){if(a=="float"){a="cssFloat"}c.style[a]=d}else{}}}}(),getXY:function(a){return ao.Dom.batch(a,ao.Dom._getXY)},_canPosition:function(a){return(ao.Dom._getStyle(a,"display")!=="none"&&ao.Dom._inDoc(a))},_getXY:function(){if(aj[av][ad]){return function(j){var i,a,h,c,d,e,f,l,k,g=Math.floor,b=false;if(ao.Dom._canPosition(j)){h=j[ad]();c=j[aM];i=ao.Dom.getDocumentScrollLeft(c);a=ao.Dom.getDocumentScrollTop(c);b=[g(h[aH]),g(h[aC])];if(aa&&aE.ie<8){d=2;e=2;f=c[ax];l=ab(c[av],aA);k=ab(c[av],ac);if(aE.ie===6){if(f!==aO){d=0;e=0}}if((f==aO)){if(l!==az){d=parseInt(l,10)}if(k!==az){e=parseInt(k,10)}}b[0]-=d;b[1]-=e}if((a||i)){b[0]+=i;b[1]+=a}b[0]=g(b[0]);b[1]=g(b[1])}else{}return b}}else{return function(h){var a,g,f,d,c,e=false,b=h;if(ao.Dom._canPosition(h)){e=[h[aP],h[ae]];a=ao.Dom.getDocumentScrollLeft(h[aM]);g=ao.Dom.getDocumentScrollTop(h[aM]);c=((am||aE.webkit>519)?true:false);while((b=b[aw])){e[0]+=b[aP];e[1]+=b[ae];if(c){e=ao.Dom._calcBorders(b,e)}}if(ao.Dom._getStyle(h,aB)!==aL){b=h;while((b=b[x])&&b[aq]){f=b[aI];d=b[af];if(am&&(ao.Dom._getStyle(b,"overflow")!=="visible")){e=ao.Dom._calcBorders(b,e)}if(f||d){e[0]-=d;e[1]-=f}}e[0]+=a;e[1]+=g}else{if(ap){e[0]-=a;e[1]-=g}else{if(al||am){e[0]+=a;e[1]+=g}}}e[0]=Math.floor(e[0]);e[1]=Math.floor(e[1])}else{}return e}}}(),getX:function(a){var b=function(c){return ao.Dom.getXY(c)[0]};return ao.Dom.batch(a,b,ao.Dom,true)},getY:function(a){var b=function(c){return ao.Dom.getXY(c)[1]};return ao.Dom.batch(a,b,ao.Dom,true)},setXY:function(b,a,c){ao.Dom.batch(b,ao.Dom._setXY,{pos:a,noRetry:c})},_setXY:function(i,f){var e=ao.Dom._getStyle(i,aB),g=ao.Dom.setStyle,b=f.pos,a=f.noRetry,d=[parseInt(ao.Dom.getComputedStyle(i,aH),10),parseInt(ao.Dom.getComputedStyle(i,aC),10)],c,h;if(e=="static"){e=G;g(i,aB,e)}c=ao.Dom._getXY(i);if(!b||c===false){return false}if(isNaN(d[0])){d[0]=(e==G)?0:i[aP]}if(isNaN(d[1])){d[1]=(e==G)?0:i[ae]}if(b[0]!==null){g(i,aH,b[0]-c[0]+d[0]+"px")}if(b[1]!==null){g(i,aC,b[1]-c[1]+d[1]+"px")}if(!a){h=ao.Dom._getXY(i);if((b[0]!==null&&h[0]!=b[0])||(b[1]!==null&&h[1]!=b[1])){ao.Dom._setXY(i,{pos:b,noRetry:true})}}},setX:function(b,a){ao.Dom.setXY(b,[a,null])},setY:function(a,b){ao.Dom.setXY(a,[null,b])},getRegion:function(a){var b=function(c){var d=false;if(ao.Dom._canPosition(c)){d=ao.Region.getRegion(c)}else{}return d};return ao.Dom.batch(a,b,ao.Dom,true)},getClientWidth:function(){return ao.Dom.getViewportWidth()},getClientHeight:function(){return ao.Dom.getViewportHeight()},getElementsByClassName:function(f,b,e,c,j,d){f=ai.trim(f);b=b||"*";e=(e)?ao.Dom.get(e):null||aj;if(!e){return[]}var a=[],k=e.getElementsByTagName(b),h=ao.Dom.hasClass;for(var i=0,g=k.length;i<g;++i){if(h(k[i],f)){a[a.length]=k[i]}}if(c){ao.Dom.batch(a,c,j,d)}return a},hasClass:function(b,a){return ao.Dom.batch(b,ao.Dom._hasClass,a)},_hasClass:function(a,c){var b=false,d;if(a&&c){d=ao.Dom.getAttribute(a,an)||ak;if(c.exec){b=c.test(d)}else{b=c&&(ar+d+ar).indexOf(ar+c+ar)>-1}}else{}return b},addClass:function(b,a){return ao.Dom.batch(b,ao.Dom._addClass,a)},_addClass:function(a,c){var b=false,d;if(a&&c){d=ao.Dom.getAttribute(a,an)||ak;if(!ao.Dom._hasClass(a,c)){ao.Dom.setAttribute(a,an,at(d+ar+c));b=true}}else{}return b},removeClass:function(b,a){return ao.Dom.batch(b,ao.Dom._removeClass,a)},_removeClass:function(f,a){var e=false,d,c,b;if(f&&a){d=ao.Dom.getAttribute(f,an)||ak;ao.Dom.setAttribute(f,an,d.replace(ao.Dom._getClassRegex(a),ak));c=ao.Dom.getAttribute(f,an);if(d!==c){ao.Dom.setAttribute(f,an,at(c));e=true;if(ao.Dom.getAttribute(f,an)===""){b=(f.hasAttribute&&f.hasAttribute(aK))?aK:an;f.removeAttribute(b)}}}else{}return e},replaceClass:function(a,c,b){return ao.Dom.batch(a,ao.Dom._replaceClass,{from:c,to:b})},_replaceClass:function(g,a){var f,c,e,b=false,d;if(g&&a){c=a.from;e=a.to;if(!e){b=false}else{if(!c){b=ao.Dom._addClass(g,a.to)}else{if(c!==e){d=ao.Dom.getAttribute(g,an)||ak;f=(ar+d.replace(ao.Dom._getClassRegex(c),ar+e)).split(ao.Dom._getClassRegex(e));f.splice(1,0,ar+e);ao.Dom.setAttribute(g,an,at(f.join(ak)));b=true}}}}else{}return b},generateId:function(b,a){a=a||"yui-gen";var c=function(e){if(e&&e.id){return e.id}var d=a+YAHOO.env._id_counter++;if(e){if(e[aM].getElementById(d)){return ao.Dom.generateId(e,d+a)}e.id=d}return d};return ao.Dom.batch(b,c,ao.Dom,true)||c.apply(ao.Dom,arguments)},isAncestor:function(c,a){c=ao.Dom.get(c);a=ao.Dom.get(a);var b=false;if((c&&a)&&(c[aF]&&a[aF])){if(c.contains&&c!==a){b=c.contains(a)}else{if(c.compareDocumentPosition){b=!!(c.compareDocumentPosition(a)&16)}}}else{}return b},inDocument:function(a,b){return ao.Dom._inDoc(ao.Dom.get(a),b)},_inDoc:function(c,a){var b=false;if(c&&c[aq]){a=a||c[aM];b=ao.Dom.isAncestor(a[av],c)}else{}return b},getElementsBy:function(a,b,f,d,i,e,c){b=b||"*";f=(f)?ao.Dom.get(f):null||aj;if(!f){return[]}var j=[],k=f.getElementsByTagName(b);for(var h=0,g=k.length;h<g;++h){if(a(k[h])){if(c){j=k[h];break}else{j[j.length]=k[h]}}}if(d){ao.Dom.batch(j,d,i,e)}return j},getElementBy:function(a,b,c){return ao.Dom.getElementsBy(a,b,c,null,null,null,true)},batch:function(a,c,f,e){var g=[],d=(e)?f:window;a=(a&&(a[aq]||a.item))?a:ao.Dom.get(a);if(a&&c){if(a[aq]||a.length===undefined){return c.call(d,a,f)}for(var b=0;b<a.length;++b){g[g.length]=c.call(d,a[b],f)}}else{return false}return g},getDocumentHeight:function(){var b=(aj[ax]!=ah||al)?aj.body.scrollHeight:z.scrollHeight,a=Math.max(b,ao.Dom.getViewportHeight());return a},getDocumentWidth:function(){var b=(aj[ax]!=ah||al)?aj.body.scrollWidth:z.scrollWidth,a=Math.max(b,ao.Dom.getViewportWidth());return a},getViewportHeight:function(){var a=self.innerHeight,b=aj[ax];if((b||aa)&&!ap){a=(b==ah)?z.clientHeight:aj.body.clientHeight}return a},getViewportWidth:function(){var a=self.innerWidth,b=aj[ax];if(b||aa){a=(b==ah)?z.clientWidth:aj.body.clientWidth}return a},getAncestorBy:function(a,b){while((a=a[x])){if(ao.Dom._testElement(a,b)){return a}}return null},getAncestorByClassName:function(c,b){c=ao.Dom.get(c);if(!c){return null}var a=function(d){return ao.Dom.hasClass(d,b)};return ao.Dom.getAncestorBy(c,a)},getAncestorByTagName:function(c,b){c=ao.Dom.get(c);if(!c){return null}var a=function(d){return d[aq]&&d[aq].toUpperCase()==b.toUpperCase()};return ao.Dom.getAncestorBy(c,a)},getPreviousSiblingBy:function(a,b){while(a){a=a.previousSibling;if(ao.Dom._testElement(a,b)){return a}}return null},getPreviousSibling:function(a){a=ao.Dom.get(a);if(!a){return null}return ao.Dom.getPreviousSiblingBy(a)},getNextSiblingBy:function(a,b){while(a){a=a.nextSibling;if(ao.Dom._testElement(a,b)){return a}}return null},getNextSibling:function(a){a=ao.Dom.get(a);if(!a){return null}return ao.Dom.getNextSiblingBy(a)},getFirstChildBy:function(b,a){var c=(ao.Dom._testElement(b.firstChild,a))?b.firstChild:null;return c||ao.Dom.getNextSiblingBy(b.firstChild,a)},getFirstChild:function(a,b){a=ao.Dom.get(a);if(!a){return null}return ao.Dom.getFirstChildBy(a)},getLastChildBy:function(b,a){if(!b){return null}var c=(ao.Dom._testElement(b.lastChild,a))?b.lastChild:null;return c||ao.Dom.getPreviousSiblingBy(b.lastChild,a)},getLastChild:function(a){a=ao.Dom.get(a);return ao.Dom.getLastChildBy(a)},getChildrenBy:function(c,d){var a=ao.Dom.getFirstChildBy(c,d),b=a?[a]:[];ao.Dom.getNextSiblingBy(a,function(e){if(!d||d(e)){b[b.length]=e}return false});return b},getChildren:function(a){a=ao.Dom.get(a);if(!a){}return ao.Dom.getChildrenBy(a)},getDocumentScrollLeft:function(a){a=a||aj;return Math.max(a[av].scrollLeft,a.body.scrollLeft)},getDocumentScrollTop:function(a){a=a||aj;return Math.max(a[av].scrollTop,a.body.scrollTop)},insertBefore:function(b,a){b=ao.Dom.get(b);a=ao.Dom.get(a);if(!b||!a||!a[x]){return null}return a[x].insertBefore(b,a)},insertAfter:function(b,a){b=ao.Dom.get(b);a=ao.Dom.get(a);if(!b||!a||!a[x]){return null}if(a.nextSibling){return a[x].insertBefore(b,a.nextSibling)}else{return a[x].appendChild(b)}},getClientRegion:function(){var a=ao.Dom.getDocumentScrollTop(),c=ao.Dom.getDocumentScrollLeft(),d=ao.Dom.getViewportWidth()+c,b=ao.Dom.getViewportHeight()+a;return new ao.Region(a,d,b,c)},setAttribute:function(c,b,a){b=ao.Dom.CUSTOM_ATTRIBUTES[b]||b;c.setAttribute(b,a)},getAttribute:function(b,a){a=ao.Dom.CUSTOM_ATTRIBUTES[a]||a;return b.getAttribute(a)},_toCamel:function(c){var a=aN;function b(e,d){return d.toUpperCase()}return a[c]||(a[c]=c.indexOf("-")===-1?c:c.replace(/-([a-z])/gi,b))},_getClassRegex:function(b){var a;if(b!==undefined){if(b.exec){a=b}else{a=aJ[b];if(!a){b=b.replace(ao.Dom._patterns.CLASS_RE_TOKENS,"\\$1");a=aJ[b]=new RegExp(ay+b+aG,Y)}}}return a},_patterns:{ROOT_TAG:/^body|html$/i,CLASS_RE_TOKENS:/([\.\(\)\^\$\*\+\?\|\[\]\{\}])/g},_testElement:function(a,b){return a&&a[aF]==1&&(!b||b(a))},_calcBorders:function(a,d){var c=parseInt(ao.Dom[au](a,ac),10)||0,b=parseInt(ao.Dom[au](a,aA),10)||0;if(am){if(ag.test(a[aq])){c=0;b=0}}d[0]+=b;d[1]+=c;return d}};var ab=ao.Dom[au];if(aE.opera){ao.Dom[au]=function(c,b){var a=ab(c,b);if(y.test(b)){a=ao.Dom.Color.toRGB(a)}return a}}if(aE.webkit){ao.Dom[au]=function(c,b){var a=ab(c,b);if(a==="rgba(0, 0, 0, 0)"){a="transparent"}return a}}})();YAHOO.util.Region=function(c,b,a,d){this.top=c;this.y=c;this[1]=c;this.right=b;this.bottom=a;this.left=d;this.x=d;this[0]=d;this.width=this.right-this.left;this.height=this.bottom-this.top};YAHOO.util.Region.prototype.contains=function(a){return(a.left>=this.left&&a.right<=this.right&&a.top>=this.top&&a.bottom<=this.bottom)};YAHOO.util.Region.prototype.getArea=function(){return((this.bottom-this.top)*(this.right-this.left))};YAHOO.util.Region.prototype.intersect=function(b){var d=Math.max(this.top,b.top),c=Math.min(this.right,b.right),a=Math.min(this.bottom,b.bottom),e=Math.max(this.left,b.left);if(a>=d&&c>=e){return new YAHOO.util.Region(d,c,a,e)}else{return null}};YAHOO.util.Region.prototype.union=function(b){var d=Math.min(this.top,b.top),c=Math.max(this.right,b.right),a=Math.max(this.bottom,b.bottom),e=Math.min(this.left,b.left);return new YAHOO.util.Region(d,c,a,e)};YAHOO.util.Region.prototype.toString=function(){return("Region {top: "+this.top+", right: "+this.right+", bottom: "+this.bottom+", left: "+this.left+", height: "+this.height+", width: "+this.width+"}")};YAHOO.util.Region.getRegion=function(d){var b=YAHOO.util.Dom.getXY(d),e=b[1],c=b[0]+d.offsetWidth,a=b[1]+d.offsetHeight,f=b[0];return new YAHOO.util.Region(e,c,a,f)};YAHOO.util.Point=function(a,b){if(YAHOO.lang.isArray(a)){b=a[1];a=a[0]}YAHOO.util.Point.superclass.constructor.call(this,b,a,b,a)};YAHOO.extend(YAHOO.util.Point,YAHOO.util.Region);(function(){var v=YAHOO.util,w="clientTop",r="clientLeft",n="parentNode",m="right",a="hasLayout",o="px",c="opacity",l="auto",t="borderLeftWidth",q="borderTopWidth",h="borderRightWidth",b="borderBottomWidth",e="visible",g="transparent",j="height",s="width",p="style",d="currentStyle",f=/^width|height$/,i=/^(\d[.\d]*)+(em|ex|px|gd|rem|vw|vh|vm|ch|mm|cm|in|pt|pc|deg|rad|ms|s|hz|khz|%){1}?/i,k={get:function(A,y){var z="",x=A[d][y];if(y===c){z=v.Dom.getStyle(A,c)}else{if(!x||(x.indexOf&&x.indexOf(o)>-1)){z=x}else{if(v.Dom.IE_COMPUTED[y]){z=v.Dom.IE_COMPUTED[y](A,y)}else{if(i.test(x)){z=v.Dom.IE.ComputedStyle.getPixel(A,y)}else{z=x}}}}return z},getOffset:function(A,z){var x=A[d][z],E=z.charAt(0).toUpperCase()+z.substr(1),D="offset"+E,C="pixel"+E,y="",B;if(x==l){B=A[D];if(B===undefined){y=0}y=B;if(f.test(z)){A[p][z]=B;if(A[D]>B){y=B-(A[D]-B)}A[p][z]=l}}else{if(!A[p][C]&&!A[p][z]){A[p][z]=x}y=A[p][C]}return y+o},getBorderWidth:function(z,x){var y=null;if(!z[d][a]){z[p].zoom=1}switch(x){case q:y=z[w];break;case b:y=z.offsetHeight-z.clientHeight-z[w];break;case t:y=z[r];break;case h:y=z.offsetWidth-z.clientWidth-z[r];break}return y+o},getPixel:function(A,B){var y=null,x=A[d][m],z=A[d][B];A[p][m]=z;y=A[p].pixelRight;A[p][m]=x;return y+o},getMargin:function(y,z){var x;if(y[d][z]==l){x=0+o}else{x=v.Dom.IE.ComputedStyle.getPixel(y,z)}return x},getVisibility:function(y,z){var x;while((x=y[d])&&x[z]=="inherit"){y=y[n]}return(x)?x[z]:e},getColor:function(x,y){return v.Dom.Color.toRGB(x[d][y])||g},getBorderColor:function(z,A){var y=z[d],x=y[A]||y.color;return v.Dom.Color.toRGB(v.Dom.Color.toHex(x))}},u={};u.top=u.right=u.bottom=u.left=u[s]=u[j]=k.getOffset;u.color=k.getColor;u[q]=u[h]=u[b]=u[t]=k.getBorderWidth;u.marginTop=u.marginRight=u.marginBottom=u.marginLeft=k.getMargin;u.visibility=k.getVisibility;u.borderColor=u.borderTopColor=u.borderRightColor=u.borderBottomColor=u.borderLeftColor=k.getBorderColor;v.Dom.IE_COMPUTED=u;v.Dom.IE_ComputedStyle=k})();(function(){var c="toString",a=parseInt,d=RegExp,b=YAHOO.util;b.Dom.Color={KEYWORDS:{black:"000",silver:"c0c0c0",gray:"808080",white:"fff",maroon:"800000",red:"f00",purple:"800080",fuchsia:"f0f",green:"008000",lime:"0f0",olive:"808000",yellow:"ff0",navy:"000080",blue:"00f",teal:"008080",aqua:"0ff"},re_RGB:/^rgb\(([0-9]+)\s*,\s*([0-9]+)\s*,\s*([0-9]+)\)$/i,re_hex:/^#?([0-9A-F]{2})([0-9A-F]{2})([0-9A-F]{2})$/i,re_hex3:/([0-9A-F])/gi,toRGB:function(e){if(!b.Dom.Color.re_RGB.test(e)){e=b.Dom.Color.toHex(e)}if(b.Dom.Color.re_hex.exec(e)){e="rgb("+[a(d.$1,16),a(d.$2,16),a(d.$3,16)].join(", ")+")"}return e},toHex:function(e){e=b.Dom.Color.KEYWORDS[e]||e;if(b.Dom.Color.re_RGB.exec(e)){var f=(d.$1.length===1)?"0"+d.$1:Number(d.$1),g=(d.$2.length===1)?"0"+d.$2:Number(d.$2),h=(d.$3.length===1)?"0"+d.$3:Number(d.$3);e=[f[c](16),g[c](16),h[c](16)].join("")}if(e.length<6){e=e.replace(b.Dom.Color.re_hex3,"$1$1")}if(e!=="transparent"&&e.indexOf("#")<0){e="#"+e}return e.toLowerCase()}}}());YAHOO.register("dom",YAHOO.util.Dom,{version:"2.7.0",build:"1799"});YAHOO.util.CustomEvent=function(c,d,e,a){this.type=c;this.scope=d||window;this.silent=e;this.signature=a||YAHOO.util.CustomEvent.LIST;this.subscribers=[];if(!this.silent){}var b="_YUICEOnSubscribe";if(c!==b){this.subscribeEvent=new YAHOO.util.CustomEvent(b,this,true)}this.lastError=null};YAHOO.util.CustomEvent.LIST=0;YAHOO.util.CustomEvent.FLAT=1;YAHOO.util.CustomEvent.prototype={subscribe:function(a,c,b){if(!a){throw new Error("Invalid callback for subscriber to '"+this.type+"'")}if(this.subscribeEvent){this.subscribeEvent.fire(a,c,b)}this.subscribers.push(new YAHOO.util.Subscriber(a,c,b))},unsubscribe:function(d,b){if(!d){return this.unsubscribeAll()}var c=false;for(var f=0,a=this.subscribers.length;f<a;++f){var e=this.subscribers[f];if(e&&e.contains(d,b)){this._delete(f);c=true}}return c},fire:function(){this.lastError=null;var g=[],m=this.subscribers.length;if(!m&&this.silent){return true}var i=[].slice.call(arguments,0),k=true,a,h=false;if(!this.silent){}var b=this.subscribers.slice(),d=YAHOO.util.Event.throwErrors;for(a=0;a<m;++a){var e=b[a];if(!e){h=true}else{if(!this.silent){}var f=e.getScope(this.scope);if(this.signature==YAHOO.util.CustomEvent.FLAT){var c=null;if(i.length>0){c=i[0]}try{k=e.fn.call(f,c,e.obj)}catch(l){this.lastError=l;if(d){throw l}}}else{try{k=e.fn.call(f,this.type,i,e.obj)}catch(j){this.lastError=j;if(d){throw j}}}if(false===k){if(!this.silent){}break}}}return(k!==false)},unsubscribeAll:function(){var a=this.subscribers.length,b;for(b=a-1;b>-1;b--){this._delete(b)}this.subscribers=[];return a},_delete:function(a){var b=this.subscribers[a];if(b){delete b.fn;delete b.obj}this.subscribers.splice(a,1)},toString:function(){return"CustomEvent: '"+this.type+"', context: "+this.scope}};YAHOO.util.Subscriber=function(a,c,b){this.fn=a;this.obj=YAHOO.lang.isUndefined(c)?null:c;this.overrideContext=b};YAHOO.util.Subscriber.prototype.getScope=function(a){if(this.overrideContext){if(this.overrideContext===true){return this.obj}else{return this.overrideContext}}return a};YAHOO.util.Subscriber.prototype.contains=function(a,b){if(b){return(this.fn==a&&this.obj==b)}else{return(this.fn==a)}};YAHOO.util.Subscriber.prototype.toString=function(){return"Subscriber { obj: "+this.obj+", overrideContext: "+(this.overrideContext||"no")+" }"};if(!YAHOO.util.Event){YAHOO.util.Event=function(){var i=false;var h=[];var g=[];var j=[];var l=[];var b=0;var k=[];var c=[];var d=0;var a={63232:38,63233:40,63234:37,63235:39,63276:33,63277:34,25:9};var f=YAHOO.env.ua.ie?"focusin":"focus";var e=YAHOO.env.ua.ie?"focusout":"blur";return{POLL_RETRYS:2000,POLL_INTERVAL:20,EL:0,TYPE:1,FN:2,WFN:3,UNLOAD_OBJ:3,ADJ_SCOPE:4,OBJ:5,OVERRIDE:6,lastError:null,isSafari:YAHOO.env.ua.webkit,webkit:YAHOO.env.ua.webkit,isIE:YAHOO.env.ua.ie,_interval:null,_dri:null,DOMReady:false,throwErrors:false,startInterval:function(){if(!this._interval){var n=this;var m=function(){n._tryPreloadAttach()};this._interval=setInterval(m,this.POLL_INTERVAL)}},onAvailable:function(m,q,o,n,p){var s=(YAHOO.lang.isString(m))?[m]:m;for(var r=0;r<s.length;r=r+1){k.push({id:s[r],fn:q,obj:o,overrideContext:n,checkReady:p})}b=this.POLL_RETRYS;this.startInterval()},onContentReady:function(m,p,o,n){this.onAvailable(m,p,o,n,true)},onDOMReady:function(o,n,m){if(this.DOMReady){setTimeout(function(){var p=window;if(m){if(m===true){p=n}else{p=m}}o.call(p,"DOMReady",[],n)},0)}else{this.DOMReadyEvent.subscribe(o,n,m)}},_addListener:function(x,z,n,t,p,A){if(!n||!n.call){return false}if(this._isValidCollection(x)){var m=true;for(var s=0,q=x.length;s<q;++s){m=this.on(x[s],z,n,t,p)&&m}return m}else{if(YAHOO.lang.isString(x)){var u=this.getEl(x);if(u){x=u}else{this.onAvailable(x,function(){YAHOO.util.Event.on(x,z,n,t,p)});return true}}}if(!x){return false}if("unload"==z&&t!==this){g[g.length]=[x,z,n,t,p];return true}var y=x;if(p){if(p===true){y=t}else{y=p}}var w=function(C){return n.call(y,YAHOO.util.Event.getEvent(C,x),t)};var B=[x,z,n,w,y,t,p];var r=h.length;h[r]=B;if(this.useLegacyEvent(x,z)){var v=this.getLegacyIndex(x,z);if(v==-1||x!=j[v][0]){v=j.length;c[x.id+z]=v;j[v]=[x,z,x["on"+z]];l[v]=[];x["on"+z]=function(C){YAHOO.util.Event.fireLegacyEvent(YAHOO.util.Event.getEvent(C),v)}}l[v].push(B)}else{try{this._simpleAdd(x,z,w,A)}catch(o){this.lastError=o;this.removeListener(x,z,n);return false}}return true},addListener:function(p,m,q,o,n){return this._addListener(p,m,q,o,n,false)},addFocusListener:function(o,p,n,m){return this._addListener(o,f,p,n,m,true)},removeFocusListener:function(m,n){return this.removeListener(m,f,n)},addBlurListener:function(o,p,n,m){return this._addListener(o,e,p,n,m,true)},removeBlurListener:function(m,n){return this.removeListener(m,e,n)},fireLegacyEvent:function(q,s){var o=true,v,m,n,u,p;m=l[s].slice();for(var t=0,r=m.length;t<r;++t){n=m[t];if(n&&n[this.WFN]){u=n[this.ADJ_SCOPE];p=n[this.WFN].call(u,q);o=(o&&p)}}v=j[s];if(v&&v[2]){v[2](q)}return o},getLegacyIndex:function(n,m){var o=this.generateId(n)+m;if(typeof c[o]=="undefined"){return -1}else{return c[o]}},useLegacyEvent:function(n,m){return(this.webkit&&this.webkit<419&&("click"==m||"dblclick"==m))},removeListener:function(w,x,o){var t,q,m;if(typeof w=="string"){w=this.getEl(w)}else{if(this._isValidCollection(w)){var n=true;for(t=w.length-1;t>-1;t--){n=(this.removeListener(w[t],x,o)&&n)}return n}}if(!o||!o.call){return this.purgeElement(w,false,x)}if("unload"==x){for(t=g.length-1;t>-1;t--){m=g[t];if(m&&m[0]==w&&m[1]==x&&m[2]==o){g.splice(t,1);return true}}return false}var s=null;var r=arguments[3];if("undefined"===typeof r){r=this._getCacheIndex(w,x,o)}if(r>=0){s=h[r]}if(!w||!s){return false}if(this.useLegacyEvent(w,x)){var u=this.getLegacyIndex(w,x);var v=l[u];if(v){for(t=0,q=v.length;t<q;++t){m=v[t];if(m&&m[this.EL]==w&&m[this.TYPE]==x&&m[this.FN]==o){v.splice(t,1);break}}}}else{try{this._simpleRemove(w,x,s[this.WFN],false)}catch(p){this.lastError=p;return false}}delete h[r][this.WFN];delete h[r][this.FN];h.splice(r,1);return true},getTarget:function(m,n){var o=m.target||m.srcElement;return this.resolveTextNode(o)},resolveTextNode:function(m){try{if(m&&3==m.nodeType){return m.parentNode}}catch(n){}return m},getPageX:function(m){var n=m.pageX;if(!n&&0!==n){n=m.clientX||0;if(this.isIE){n+=this._getScrollLeft()}}return n},getPageY:function(n){var m=n.pageY;if(!m&&0!==m){m=n.clientY||0;if(this.isIE){m+=this._getScrollTop()}}return m},getXY:function(m){return[this.getPageX(m),this.getPageY(m)]},getRelatedTarget:function(m){var n=m.relatedTarget;if(!n){if(m.type=="mouseout"){n=m.toElement}else{if(m.type=="mouseover"){n=m.fromElement}}}return this.resolveTextNode(n)},getTime:function(m){if(!m.time){var n=new Date().getTime();try{m.time=n}catch(o){this.lastError=o;return n}}return m.time},stopEvent:function(m){this.stopPropagation(m);this.preventDefault(m)},stopPropagation:function(m){if(m.stopPropagation){m.stopPropagation()}else{m.cancelBubble=true}},preventDefault:function(m){if(m.preventDefault){m.preventDefault()}else{m.returnValue=false}},getEvent:function(n,p){var o=n||window.event;if(!o){var m=this.getEvent.caller;while(m){o=m.arguments[0];if(o&&Event==o.constructor){break}m=m.caller}}return o},getCharCode:function(m){var n=m.keyCode||m.charCode||0;if(YAHOO.env.ua.webkit&&(n in a)){n=a[n]}return n},_getCacheIndex:function(n,m,o){for(var p=0,q=h.length;p<q;p=p+1){var r=h[p];if(r&&r[this.FN]==o&&r[this.EL]==n&&r[this.TYPE]==m){return p}}return -1},generateId:function(n){var m=n.id;if(!m){m="yuievtautoid-"+d;++d;n.id=m}return m},_isValidCollection:function(m){try{return(m&&typeof m!=="string"&&m.length&&!m.tagName&&!m.alert&&typeof m[0]!=="undefined")}catch(n){return false}},elCache:{},getEl:function(m){return(typeof m==="string")?document.getElementById(m):m},clearCache:function(){},DOMReadyEvent:new YAHOO.util.CustomEvent("DOMReady",this),_load:function(m){if(!i){i=true;var n=YAHOO.util.Event;n._ready();n._tryPreloadAttach()}},_ready:function(m){var n=YAHOO.util.Event;if(!n.DOMReady){n.DOMReady=true;n.DOMReadyEvent.fire();n._simpleRemove(document,"DOMContentLoaded",n._ready)}},_tryPreloadAttach:function(){if(k.length===0){b=0;if(this._interval){clearInterval(this._interval);this._interval=null}return}if(this.locked){return}if(this.isIE){if(!this.DOMReady){this.startInterval();return}}this.locked=true;var n=!i;if(!n){n=(b>0&&k.length>0)}var o=[];var m=function(v,u){var w=v;if(u.overrideContext){if(u.overrideContext===true){w=u.obj}else{w=u.overrideContext}}u.fn.call(w,u.obj)};var s,t,p,q,r=[];for(s=0,t=k.length;s<t;s=s+1){p=k[s];if(p){q=this.getEl(p.id);if(q){if(p.checkReady){if(i||q.nextSibling||!n){r.push(p);k[s]=null}}else{m(q,p);k[s]=null}}else{o.push(p)}}}for(s=0,t=r.length;s<t;s=s+1){p=r[s];m(this.getEl(p.id),p)}b--;if(n){for(s=k.length-1;s>-1;s--){p=k[s];if(!p||!p.id){k.splice(s,1)}}this.startInterval()}else{if(this._interval){clearInterval(this._interval);this._interval=null}}this.locked=false},purgeElement:function(p,o,m){var r=(YAHOO.lang.isString(p))?this.getEl(p):p;var n=this.getListeners(r,m),q,t;if(n){for(q=n.length-1;q>-1;q--){var s=n[q];this.removeListener(r,s.type,s.fn)}}if(o&&r&&r.childNodes){for(q=0,t=r.childNodes.length;q<t;++q){this.purgeElement(r.childNodes[q],o,m)}}},getListeners:function(t,v){var q=[],u;if(!v){u=[h,g]}else{if(v==="unload"){u=[g]}else{u=[h]}}var o=(YAHOO.lang.isString(t))?this.getEl(t):t;for(var r=0;r<u.length;r=r+1){var m=u[r];if(m){for(var p=0,n=m.length;p<n;++p){var s=m[p];if(s&&s[this.EL]===o&&(!v||v===s[this.TYPE])){q.push({type:s[this.TYPE],fn:s[this.FN],obj:s[this.OBJ],adjust:s[this.OVERRIDE],scope:s[this.ADJ_SCOPE],index:p})}}}}return(q.length)?q:null},_unload:function(n){var t=YAHOO.util.Event,q,r,s,o,p,m=g.slice(),u;for(q=0,o=g.length;q<o;++q){s=m[q];if(s){u=window;if(s[t.ADJ_SCOPE]){if(s[t.ADJ_SCOPE]===true){u=s[t.UNLOAD_OBJ]}else{u=s[t.ADJ_SCOPE]}}s[t.FN].call(u,t.getEvent(n,s[t.EL]),s[t.UNLOAD_OBJ]);m[q]=null}}s=null;u=null;g=null;if(h){for(r=h.length-1;r>-1;r--){s=h[r];if(s){t.removeListener(s[t.EL],s[t.TYPE],s[t.FN],r)}}s=null}j=null;t._simpleRemove(window,"unload",t._unload)},_getScrollLeft:function(){return this._getScroll()[1]},_getScrollTop:function(){return this._getScroll()[0]},_getScroll:function(){var n=document.documentElement,m=document.body;if(n&&(n.scrollTop||n.scrollLeft)){return[n.scrollTop,n.scrollLeft]}else{if(m){return[m.scrollTop,m.scrollLeft]}else{return[0,0]}}},regCE:function(){},_simpleAdd:function(){if(window.addEventListener){return function(n,m,o,p){n.addEventListener(m,o,(p))}}else{if(window.attachEvent){return function(n,m,o,p){n.attachEvent("on"+m,o)}}else{return function(){}}}}(),_simpleRemove:function(){if(window.removeEventListener){return function(n,m,o,p){n.removeEventListener(m,o,(p))}}else{if(window.detachEvent){return function(n,m,o){n.detachEvent("on"+m,o)}}else{return function(){}}}}()}}();(function(){var a=YAHOO.util.Event;a.on=a.addListener;a.onFocus=a.addFocusListener;a.onBlur=a.addBlurListener;if(a.isIE){YAHOO.util.Event.onDOMReady(YAHOO.util.Event._tryPreloadAttach,YAHOO.util.Event,true);var b=document.createElement("p");a._dri=setInterval(function(){try{b.doScroll("left");clearInterval(a._dri);a._dri=null;a._ready();b=null}catch(c){}},a.POLL_INTERVAL)}else{if(a.webkit&&a.webkit<525){a._dri=setInterval(function(){var c=document.readyState;if("loaded"==c||"complete"==c){clearInterval(a._dri);a._dri=null;a._ready()}},a.POLL_INTERVAL)}else{a._simpleAdd(document,"DOMContentLoaded",a._ready)}}a._simpleAdd(window,"load",a._load);a._simpleAdd(window,"unload",a._unload);a._tryPreloadAttach()})()}YAHOO.util.EventProvider=function(){};YAHOO.util.EventProvider.prototype={__yui_events:null,__yui_subscribers:null,subscribe:function(a,e,b,c){this.__yui_events=this.__yui_events||{};var d=this.__yui_events[a];if(d){d.subscribe(e,b,c)}else{this.__yui_subscribers=this.__yui_subscribers||{};var f=this.__yui_subscribers;if(!f[a]){f[a]=[]}f[a].push({fn:e,obj:b,overrideContext:c})}},unsubscribe:function(f,d,b){this.__yui_events=this.__yui_events||{};var a=this.__yui_events;if(f){var c=a[f];if(c){return c.unsubscribe(d,b)}}else{var g=true;for(var e in a){if(YAHOO.lang.hasOwnProperty(a,e)){g=g&&a[e].unsubscribe(d,b)}}return g}return false},unsubscribeAll:function(a){return this.unsubscribe(a)},createEvent:function(g,a){this.__yui_events=this.__yui_events||{};var d=a||{};var e=this.__yui_events;if(e[g]){}else{var f=d.scope||this;var i=(d.silent);var c=new YAHOO.util.CustomEvent(g,f,i,YAHOO.util.CustomEvent.FLAT);e[g]=c;if(d.onSubscribeCallback){c.subscribeEvent.subscribe(d.onSubscribeCallback)}this.__yui_subscribers=this.__yui_subscribers||{};var h=this.__yui_subscribers[g];if(h){for(var b=0;b<h.length;++b){c.subscribe(h[b].fn,h[b].obj,h[b].overrideContext)}}}return e[g]},fireEvent:function(d,e,a,f){this.__yui_events=this.__yui_events||{};var b=this.__yui_events[d];if(!b){return null}var g=[];for(var c=1;c<arguments.length;++c){g.push(arguments[c])}return b.fire.apply(b,g)},hasEvent:function(a){if(this.__yui_events){if(this.__yui_events[a]){return true}}return false}};(function(){var a=YAHOO.util.Event,b=YAHOO.lang;YAHOO.util.KeyListener=function(i,d,h,g){if(!i){}else{if(!d){}else{if(!h){}}}if(!g){g=YAHOO.util.KeyListener.KEYDOWN}var f=new YAHOO.util.CustomEvent("keyPressed");this.enabledEvent=new YAHOO.util.CustomEvent("enabled");this.disabledEvent=new YAHOO.util.CustomEvent("disabled");if(b.isString(i)){i=document.getElementById(i)}if(b.isFunction(h)){f.subscribe(h)}else{f.subscribe(h.fn,h.scope,h.correctScope)}function e(m,n){if(!d.shift){d.shift=false}if(!d.alt){d.alt=false}if(!d.ctrl){d.ctrl=false}if(m.shiftKey==d.shift&&m.altKey==d.alt&&m.ctrlKey==d.ctrl){var l,o=d.keys,j;if(YAHOO.lang.isArray(o)){for(var k=0;k<o.length;k++){l=o[k];j=a.getCharCode(m);if(l==j){f.fire(j,m);break}}}else{j=a.getCharCode(m);if(o==j){f.fire(j,m)}}}}this.enable=function(){if(!this.enabled){a.on(i,g,e);this.enabledEvent.fire(d)}this.enabled=true};this.disable=function(){if(this.enabled){a.removeListener(i,g,e);this.disabledEvent.fire(d)}this.enabled=false};this.toString=function(){return"KeyListener ["+d.keys+"] "+i.tagName+(i.id?"["+i.id+"]":"")}};var c=YAHOO.util.KeyListener;c.KEYDOWN="keydown";c.KEYUP="keyup";c.KEY={ALT:18,BACK_SPACE:8,CAPS_LOCK:20,CONTROL:17,DELETE:46,DOWN:40,END:35,ENTER:13,ESCAPE:27,HOME:36,LEFT:37,META:224,NUM_LOCK:144,PAGE_DOWN:34,PAGE_UP:33,PAUSE:19,PRINTSCREEN:44,RIGHT:39,SCROLL_LOCK:145,SHIFT:16,SPACE:32,TAB:9,UP:38}})();YAHOO.register("event",YAHOO.util.Event,{version:"2.7.0",build:"1799"});YAHOO.register("yahoo-dom-event",YAHOO,{version:"2.7.0",build:"1799"});(function(){var a=YAHOO.util;a.Selector={_foundCache:[],_regexCache:{},_re:{nth:/^(?:([-]?\d*)(n){1}|(odd|even)$)*([-+]?\d*)$/,attr:/(\[.*\])/g,urls:/^(?:href|src)/},document:window.document,attrAliases:{},shorthand:{"\\#(-?[_a-z]+[-\\w]*)":"[id=$1]","\\.(-?[_a-z]+[-\\w]*)":"[class~=$1]"},operators:{"=":function(c,b){return c===b},"!=":function(c,b){return c!==b},"~=":function(d,b){var c=" ";return(c+d+c).indexOf((c+b+c))>-1},"|=":function(c,b){return c===b||c.slice(0,b.length+1)===b+"-"},"^=":function(c,b){return c.indexOf(b)===0},"$=":function(c,b){return c.slice(-b.length)===b},"*=":function(c,b){return c.indexOf(b)>-1},"":function(c,b){return c}},pseudos:{root:function(b){return b===b.ownerDocument.documentElement},"nth-child":function(c,b){return a.Selector._getNth(c,b)},"nth-last-child":function(c,b){return a.Selector._getNth(c,b,null,true)},"nth-of-type":function(c,b){return a.Selector._getNth(c,b,c.tagName)},"nth-last-of-type":function(c,b){return a.Selector._getNth(c,b,c.tagName,true)},"first-child":function(b){return a.Selector._getChildren(b.parentNode)[0]===b},"last-child":function(b){var c=a.Selector._getChildren(b.parentNode);return c[c.length-1]===b},"first-of-type":function(c,b){return a.Selector._getChildren(c.parentNode,c.tagName)[0]},"last-of-type":function(c,b){var d=a.Selector._getChildren(c.parentNode,c.tagName);return d[d.length-1]},"only-child":function(b){var c=a.Selector._getChildren(b.parentNode);return c.length===1&&c[0]===b},"only-of-type":function(b){return a.Selector._getChildren(b.parentNode,b.tagName).length===1},empty:function(b){return b.childNodes.length===0},not:function(c,b){return !a.Selector.test(c,b)},contains:function(d,b){var c=d.innerText||d.textContent||"";return c.indexOf(b)>-1},checked:function(b){return b.checked===true}},test:function(b,d){b=a.Selector.document.getElementById(b)||b;if(!b){return false}var e=d?d.split(","):[];if(e.length){for(var c=0,f=e.length;c<f;++c){if(a.Selector._test(b,e[c])){return true}}return false}return a.Selector._test(b,d)},_test:function(b,i,j,k){j=j||a.Selector._tokenize(i).pop()||{};if(!b.tagName||(j.tag!=="*"&&b.tagName!==j.tag)||(k&&b._found)){return false}if(j.attributes.length){var d,h,c=a.Selector._re.urls;if(!b.attributes||!b.attributes.length){return false}for(var g=0,e;e=j.attributes[g++];){h=(c.test(e[0]))?2:0;d=b.getAttribute(e[0],h);if(d===null||d===undefined){return false}if(a.Selector.operators[e[1]]&&!a.Selector.operators[e[1]](d,e[2])){return false}}}if(j.pseudos.length){for(var g=0,f=j.pseudos.length;g<f;++g){if(a.Selector.pseudos[j.pseudos[g][0]]&&!a.Selector.pseudos[j.pseudos[g][0]](b,j.pseudos[g][1])){return false}}}return(j.previous&&j.previous.combinator!==",")?a.Selector._combinators[j.previous.combinator](b,j):true},filter:function(e,f){e=e||[];var c,g=[],b=a.Selector._tokenize(f);if(!e.item){for(var d=0,h=e.length;d<h;++d){if(!e[d].tagName){c=a.Selector.document.getElementById(e[d]);if(c){e[d]=c}else{}}}}g=a.Selector._filter(e,a.Selector._tokenize(f)[0]);return g},_filter:function(f,d,c,g){var h=c?null:[],b=a.Selector._foundCache;for(var e=0,i=f.length;e<i;e++){if(!a.Selector._test(f[e],"",d,g)){continue}if(c){return f[e]}if(g){if(f[e]._found){continue}f[e]._found=true;b[b.length]=f[e]}h[h.length]=f[e]}return h},query:function(d,c,b){var e=a.Selector._query(d,c,b);return e},_query:function(m,h,g,o){var e=(g)?null:[],p;if(!m){return e}var b=m.split(",");if(b.length>1){var f;for(var l=0,k=b.length;l<k;++l){f=arguments.callee(b[l],h,g,true);e=g?f:e.concat(f)}a.Selector._clearFoundCache();return e}if(h&&!h.nodeName){h=a.Selector.document.getElementById(h);if(!h){return e}}h=h||a.Selector.document;if(h.nodeName!=="#document"){a.Dom.generateId(h);m=h.tagName+"#"+h.id+" "+m;p=h;h=h.ownerDocument}var i=a.Selector._tokenize(m);var j=i[a.Selector._getIdTokenIndex(i)],d=[],c,n=i.pop()||{};if(j){c=a.Selector._getId(j.attributes)}if(c){p=p||a.Selector.document.getElementById(c);if(p&&(h.nodeName==="#document"||a.Dom.isAncestor(h,p))){if(a.Selector._test(p,null,j)){if(j===n){d=[p]}else{if(j.combinator===" "||j.combinator===">"){h=p}}}}else{return e}}if(h&&!d.length){d=h.getElementsByTagName(n.tag)}if(d.length){e=a.Selector._filter(d,n,g,o)}return e},_clearFoundCache:function(){var b=a.Selector._foundCache;for(var d=0,e=b.length;d<e;++d){try{delete b[d]._found}catch(c){b[d].removeAttribute("_found")}}b=[]},_getRegExp:function(b,d){var c=a.Selector._regexCache;d=d||"";if(!c[b+d]){c[b+d]=new RegExp(b,d)}return c[b+d]},_getChildren:function(){if(document.documentElement.children){return function(b,c){return(c)?b.children.tags(c):b.children||[]}}else{return function(c,f){if(c._children){return c._children}var d=[],b=c.childNodes;for(var e=0,g=b.length;e<g;++e){if(b[e].tagName){if(!f||b[e].tagName===f){d[d.length]=b[e]}}}c._children=d;return d}}}(),_combinators:{" ":function(b,c){while((b=b.parentNode)){if(a.Selector._test(b,"",c.previous)){return true}}return false},">":function(b,c){return a.Selector._test(b.parentNode,null,c.previous)},"+":function(b,c){var d=b.previousSibling;while(d&&d.nodeType!==1){d=d.previousSibling}if(d&&a.Selector._test(d,null,c.previous)){return true}return false},"~":function(b,c){var d=b.previousSibling;while(d){if(d.nodeType===1&&a.Selector._test(d,null,c.previous)){return true}d=d.previousSibling}return false}},_getNth:function(c,g,e,l){a.Selector._re.nth.test(g);var h=parseInt(RegExp.$1,10),d=RegExp.$2,k=RegExp.$3,j=parseInt(RegExp.$4,10)||0,f=[],n;var i=a.Selector._getChildren(c.parentNode,e);if(k){h=2;n="+";d="n";j=(k==="odd")?1:0}else{if(isNaN(h)){h=(d)?1:0}}if(h===0){if(l){j=i.length-j+1}if(i[j-1]===c){return true}else{return false}}else{if(h<0){l=!!l;h=Math.abs(h)}}if(!l){for(var b=j-1,m=i.length;b<m;b+=h){if(b>=0&&i[b]===c){return true}}}else{for(var b=i.length-j,m=i.length;b>=0;b-=h){if(b<m&&i[b]===c){return true}}}return false},_getId:function(c){for(var b=0,d=c.length;b<d;++b){if(c[b][0]=="id"&&c[b][1]==="="){return c[b][2]}}},_getIdTokenIndex:function(b){for(var c=0,d=b.length;c<d;++c){if(a.Selector._getId(b[c].attributes)){return c}}return -1},_patterns:{tag:/^((?:-?[_a-z]+[\w-]*)|\*)/i,attributes:/^\[([a-z]+\w*)+([~\|\^\$\*!=]=?)?['"]?([^\]]*?)['"]?\]/i,pseudos:/^:([-\w]+)(?:\(['"]?(.+)['"]?\))*/i,combinator:/^\s*([>+~]|\s)\s*/},_tokenize:function(i){var g={},c=[],b,d=false,e=a.Selector._patterns,h;i=a.Selector._replaceShorthand(i);do{d=false;for(var f in e){if(YAHOO.lang.hasOwnProperty(e,f)){if(f!="tag"&&f!="combinator"){g[f]=g[f]||[]}if((h=e[f].exec(i))){d=true;if(f!="tag"&&f!="combinator"){if(f==="attributes"&&h[1]==="id"){g.id=h[3]}g[f].push(h.slice(1))}else{g[f]=h[1]}i=i.replace(h[0],"");if(f==="combinator"||!i.length){g.attributes=a.Selector._fixAttributes(g.attributes);g.pseudos=g.pseudos||[];g.tag=g.tag?g.tag.toUpperCase():"*";c.push(g);g={previous:g}}}}}}while(d);return c},_fixAttributes:function(d){var c=a.Selector.attrAliases;d=d||[];for(var b=0,e=d.length;b<e;++b){if(c[d[b][0]]){d[b][0]=c[d[b][0]]}if(!d[b][1]){d[b][1]=""}}return d},_replaceShorthand:function(f){var e=a.Selector.shorthand;var d=f.match(a.Selector._re.attr);if(d){f=f.replace(a.Selector._re.attr,"REPLACED_ATTRIBUTE")}for(var b in e){if(YAHOO.lang.hasOwnProperty(e,b)){f=f.replace(a.Selector._getRegExp(b,"gi"),e[b])}}if(d){for(var c=0,g=d.length;c<g;++c){f=f.replace("REPLACED_ATTRIBUTE",d[c])}}return f}};if(YAHOO.env.ua.ie&&YAHOO.env.ua.ie<8){a.Selector.attrAliases["class"]="className";a.Selector.attrAliases["for"]="htmlFor"}})();YAHOO.register("selector",YAHOO.util.Selector,{version:"2.7.0",build:"1799"});var $Y=YAHOO.util,$D=$Y.Dom,$E=$Y.Event,$$=$Y.Selector.query,$=$D.get,FD=YAHOO.namespace("FD");var FYU=YAHOO.util,FYD=$Y.Dom,FYE=$Y.Event,FYS=$Y.Selector.query,FYG=$D.get;FD.namespace=function(){var a=Array.prototype.slice.call(arguments,0),b;for(b=0;b<a.length;++b){if(a[b].indexOf("FD")!=0){a[b]="FD."+a[b]}}return YAHOO.namespace.apply(null,a)};FD.namespace("core","util","widget");if(!Array.prototype.indexOf){Array.prototype.indexOf=function(c,b){if(b==null){b=0}else{if(b<0){b=Math.max(0,this.length+b)}}for(var a=b;a<this.length;a++){if(this[a]===c){return a}}return -1}}if(!Array.prototype.lastIndexOf){Array.prototype.lastIndexOf=function(c,b){if(b==null){b=this.length-1}else{if(b<0){b=Math.max(0,this.length+b)}}for(var a=b;a>=0;a--){if(this[a]===c){return a}}return -1}}if(!Array.prototype.forEach){Array.prototype.forEach=function(c,d){var a=this.length;for(var b=0;b<a;b++){c.call(d,this[b],b,this)}}}if(!Array.prototype.filter){Array.prototype.filter=function(d,e){var a=this.length;var c=[];for(var b=0;b<a;b++){if(d.call(e,this[b],b,this)){c.push(this[b])}}return c}}if(!Array.prototype.map){Array.prototype.map=function(d,e){var a=this.length;var c=[];for(var b=0;b<a;b++){c.push(d.call(e,this[b],b,this))}return c}}if(!Array.prototype.some){Array.prototype.some=function(c,d){var a=this.length;for(var b=0;b<a;b++){if(c.call(d,this[b],b,this)){return true}}return false}}if(!Array.prototype.every){Array.prototype.every=function(c,d){var a=this.length;for(var b=0;b<a;b++){if(!c.call(d,this[b],b,this)){return false}}return true}}Array.prototype.contains=function(a){return this.indexOf(a)!=-1};Array.prototype.copy=function(a){return this.concat()};Array.prototype.insertAt=function(b,a){this.splice(a,0,b)};Array.prototype.insertBefore=function(c,b){var a=this.indexOf(b);if(a==-1){this.push(c)}else{this.splice(a,0,c)}};Array.prototype.removeAt=function(a){this.splice(a,1)};Array.prototype.remove=function(b){var a=this.indexOf(b);if(a!=-1){this.splice(a,1)}};Array.prototype.del=function(a){if(a<0){return this}return this.slice(0,a).concat(this.slice(a+1,this.length))};if(!String.prototype.toQueryParams){String.prototype.toQueryParams=function(){var f={};var g=this.split("&");var d=/([^=]*)=(.*)/;for(var b=0;b<g.length;b++){var a=d.exec(g[b]);if(!a){continue}var c=decodeURIComponent(a[1]);var e=a[2]?decodeURIComponent(a[2]):undefined;if(f[c]!==undefined){if(f[c].constructor!=Array){f[c]=[f[c]]}if(e){f[c].push(e)}}else{f[c]=e}}return f}}if(!String.prototype.trim){String.prototype.trim=function(){var a=/^\s+|\s+$/g;return function(){return this.replace(a,"")}}()}if(!String.prototype.replaceAll){String.prototype.replaceAll=function(b,a){return this.replace(new RegExp(b,"gm"),a)}}if(!String.prototype.lenB){String.prototype.lenB=function(){return this.replace(/[^\x00-\xff]/g,"**").length}}if(!String.prototype.cut){String.prototype.cut=function(a){var e=this;if(e=="undefined"){return""}var b=0;a=parseInt(a);if(e.lenB()<=a){return e}for(var c=0;c<e.length;c++){var d=e.charCodeAt(c);if(d<0||d>255){b+=2}else{b++}if(b>a){return e.substr(0,c==0?c=1:c)}}return""}}FD.common={trim:function(a){return a.replace(/(^\s*)|(\s*$)/g,"")},escapeHTML:function(b){var c=document.createElement("div");var a=document.createTextNode(b);c.appendChild(a);return c.innerHTML},unescapeHTML:function(a){var b=document.createElement("div");b.innerHTML=a.replace(/<\/?[^>]+>/gi,"");return b.childNodes[0]?b.childNodes[0].nodeValue:""},stripTags:function(a){return a.replace(/<\/?[^>]+>/gi,"")},toArray:function(b,d){var c=[];for(var a=d||0;a<b.length;a++){c[c.length]=b[a]}return c},applyIf:function(c,a){if(c&&a&&typeof a=="object"){for(var b in a){if(!YAHOO.lang.hasOwnProperty(c,b)){c[b]=a[b]}}}return c},apply:function(c,a){if(c&&a&&typeof a=="object"){for(var b in a){c[b]=a[b]}}return c},goTo:function(e){var d=document.createElement("a"),c="_blank",f=document.body,g=arguments[1]==c?c:"_self";if(!d.click){return window.open(e,g)}d.setAttribute("target",g);d.setAttribute("href",e);d.style.display="none";if(!f){return}f.appendChild(d);d.click();if(g==c){setTimeout(function(){try{f.removeChild(d)}catch(a){}},500)}},concat:function(){var d=arguments;var a=[],b,f;outer:for(var e=0,b=d.length;e<b;e++){inner:for(var c=0,f=d[e].length;c<f;c++){a.push(d[e][c])}}return a},getFormAction:function(a){a=FYG(a);if(a&&a.tagName.toLowerCase()=="form"){return a.attributes.getNamedItem("action").value}return null},formSerialize:function(a,c,b){a=FYG(a);b=b||"&";var k="";if(a&&a.tagName.toLowerCase()=="form"){var h=a.getElementsByTagName("input");var d=a.getElementsByTagName("select");var l=a.getElementsByTagName("textarea");h=FD.common.concat(h,d,l);var g=h.length;while(g-->0){var f=h[g].name+"="+encodeURIComponent(h[g].value);if(k==""){k+=f}else{k+=b+f}}}if(YAHOO.lang.isObject(c)){for(var j in c){var f=j+"="+encodeURIComponent(c[j]);if(k==""){k+=f}else{k+=b+f}}}return k},parse:function(str){var jsn;try{jsn=YAHOO.lang.JSON.parse(str)}catch(x){try{eval("jsn = "+str)}catch(e){}}return jsn}};FD.version="0.6.2009-5-5";
/*
Copyright (c) 2009, Yahoo! Inc. All rights reserved.
Code licensed under the BSD License:
http://developer.yahoo.net/yui/license.txt
version: 2.7.0
*/
YAHOO.util.Get=function(){var M={},L=0,R=0,E=false,N=YAHOO.env.ua,S=YAHOO.lang;var J=function(W,T,X){var U=X||window,Y=U.document,Z=Y.createElement(W);for(var V in T){if(T[V]&&YAHOO.lang.hasOwnProperty(T,V)){Z.setAttribute(V,T[V]);}}return Z;};var I=function(T,U,W){var V=W||"utf-8";return J("link",{"id":"yui__dyn_"+(R++),"type":"text/css","charset":V,"rel":"stylesheet","href":T},U);};var P=function(T,U,W){var V=W||"utf-8";return J("script",{"id":"yui__dyn_"+(R++),"type":"text/javascript","charset":V,"src":T},U);};var A=function(T,U){return{tId:T.tId,win:T.win,data:T.data,nodes:T.nodes,msg:U,purge:function(){D(this.tId);}};};var B=function(T,W){var U=M[W],V=(S.isString(T))?U.win.document.getElementById(T):T;if(!V){Q(W,"target node not found: "+T);}return V;};var Q=function(W,V){var T=M[W];if(T.onFailure){var U=T.scope||T.win;T.onFailure.call(U,A(T,V));}};var C=function(W){var T=M[W];T.finished=true;if(T.aborted){var V="transaction "+W+" was aborted";Q(W,V);return;}if(T.onSuccess){var U=T.scope||T.win;T.onSuccess.call(U,A(T));}};var O=function(V){var T=M[V];if(T.onTimeout){var U=T.scope||T;T.onTimeout.call(U,A(T));}};var G=function(V,Z){var U=M[V];if(U.timer){U.timer.cancel();}if(U.aborted){var X="transaction "+V+" was aborted";Q(V,X);return;}if(Z){U.url.shift();if(U.varName){U.varName.shift();}}else{U.url=(S.isString(U.url))?[U.url]:U.url;if(U.varName){U.varName=(S.isString(U.varName))?[U.varName]:U.varName;}}var c=U.win,b=c.document,a=b.getElementsByTagName("head")[0],W;if(U.url.length===0){if(U.type==="script"&&N.webkit&&N.webkit<420&&!U.finalpass&&!U.varName){var Y=P(null,U.win,U.charset);Y.innerHTML='YAHOO.util.Get._finalize("'+V+'");';U.nodes.push(Y);a.appendChild(Y);}else{C(V);}return;}var T=U.url[0];if(!T){U.url.shift();return G(V);}if(U.timeout){U.timer=S.later(U.timeout,U,O,V);}if(U.type==="script"){W=P(T,c,U.charset);}else{W=I(T,c,U.charset);}F(U.type,W,V,T,c,U.url.length);U.nodes.push(W);if(U.insertBefore){var e=B(U.insertBefore,V);if(e){e.parentNode.insertBefore(W,e);}}else{a.appendChild(W);}if((N.webkit||N.gecko)&&U.type==="css"){G(V,T);}};var K=function(){if(E){return;}E=true;for(var T in M){var U=M[T];if(U.autopurge&&U.finished){D(U.tId);delete M[T];}}E=false;};var D=function(a){var X=M[a];if(X){var Z=X.nodes,T=Z.length,Y=X.win.document,W=Y.getElementsByTagName("head")[0];if(X.insertBefore){var V=B(X.insertBefore,a);if(V){W=V.parentNode;}}for(var U=0;U<T;U=U+1){W.removeChild(Z[U]);}X.nodes=[];}};var H=function(U,T,V){var X="q"+(L++);V=V||{};if(L%YAHOO.util.Get.PURGE_THRESH===0){K();}M[X]=S.merge(V,{tId:X,type:U,url:T,finished:false,aborted:false,nodes:[]});var W=M[X];W.win=W.win||window;W.scope=W.scope||W.win;W.autopurge=("autopurge" in W)?W.autopurge:(U==="script")?true:false;S.later(0,W,G,X);return{tId:X};};var F=function(c,X,W,U,Y,Z,b){var a=b||G;if(N.ie){X.onreadystatechange=function(){var d=this.readyState;if("loaded"===d||"complete"===d){X.onreadystatechange=null;a(W,U);}};}else{if(N.webkit){if(c==="script"){if(N.webkit>=420){X.addEventListener("load",function(){a(W,U);});}else{var T=M[W];if(T.varName){var V=YAHOO.util.Get.POLL_FREQ;T.maxattempts=YAHOO.util.Get.TIMEOUT/V;T.attempts=0;T._cache=T.varName[0].split(".");T.timer=S.later(V,T,function(j){var f=this._cache,e=f.length,d=this.win,g;for(g=0;g<e;g=g+1){d=d[f[g]];if(!d){this.attempts++;if(this.attempts++>this.maxattempts){var h="Over retry limit, giving up";T.timer.cancel();Q(W,h);}else{}return;}}T.timer.cancel();a(W,U);},null,true);}else{S.later(YAHOO.util.Get.POLL_FREQ,null,a,[W,U]);}}}}else{X.onload=function(){a(W,U);};}}};return{POLL_FREQ:10,PURGE_THRESH:20,TIMEOUT:2000,_finalize:function(T){S.later(0,null,C,T);},abort:function(U){var V=(S.isString(U))?U:U.tId;var T=M[V];if(T){T.aborted=true;}},script:function(T,U){return H("script",T,U);},css:function(T,U){return H("css",T,U);}};}();YAHOO.register("get",YAHOO.util.Get,{version:"2.7.0",build:"1799"});
/// <reference path="fdev.js"/>
/**
* Copyright (c) 2009, Alibaba! Inc. All rights reserved.
* version: 20090220
* js日历
* @author alucard.yuy@alibaba-inc.com
* 暂时不要和reset.css混用，下一版修复不兼容reset.css问题
*/
FD.widget.Calender = function(config) {
this.init(config);
}
FD.widget.Calender.defConfig = {
inputObj : null,
targetObj : null,
_date : null,
calYearsel : null,
calMonthsel : null,
calbottom : null,
calYear : 0,
calMonth : 0,
calDay : 0,
MonthDays : new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31),
DayArr : [],
showMoreDay : false,
Dragable : false,
XOffset : 0, //自定义日历位置垂直偏移
YOffset : 0,  //自定义日历位置水平偏移
beginDate : '', //定义日期开始范围
endDate : '' //定义日期结束范围
};
FD.widget.Calender.prototype = {
getStyle: function() {
var StyleStr = "";
return StyleStr;
},
getHeader: function() {
var Day = new Array('日', '一', '二', '三', '四', '五', '六');
var HeadStr = "<thead class ='candrag'><tr class='candrag'><th colspan='7' class='calhead candrag'><div id='calheader candrag'>";
HeadStr += "<a class='calnavleft' id='calnavleft'></a><select id='calYearsel' style='margin-left:3px;margin-top:5px;width:65px;font-size:12px;'></select><select id='calMonthsel' style='margin-left:5px;width:48px;font-size:12px;'></select><a class='calnavright' id='calnavright'></a></div></th></tr><tr class='calweekdayrow candrag' style='height:20px;'>";
for (var i = 0; i < 7; i++) {
HeadStr += "<th class='calweekdaycell'>" + Day[i] + "</th>";
}
HeadStr += "</tr></thead>";
return HeadStr;
},
getBody: function() {
var BodyStr = "";
var n = 0;
BodyStr = "<tbody>";
for (i = 0; i < 6; i++) {
BodyStr += "<tr align='center'>";
for (j = 0; j < 7; j++) {
BodyStr += "<td class='calpoint' id='calDay" + (n++) + "'></td>";
}
BodyStr += "</tr>";
}
BodyStr += "<tr><td colspan='7' style='border:0px;'><table><tr><td style='width:140px;' class='calbottom' id='calbottom'></td><td class='calbottom' style='width:30px;'><div style='cursor:pointer;' id='clearbutton'>清除</div></td></tr></table></td></tr>";
BodyStr += "</tbody>";
return BodyStr;
},
init: function(config) {
this.defConfig = FD.common.applyIf(config||{}, FD.widget.Calender.defConfig);
var str = this.getStyle();
var calcontainer = document.createElement('div');
calcontainer.id = 'fd-container';
document.body.appendChild(calcontainer);
$D.addClass('fd-container', 'fd-calcontainer');
$D.setStyle('fd-container', 'display', 'none');
str += "<table style='width:170px;position:absolute;left:3;top:3;z-index:10;'>";
str += this.getHeader();
str += this.getBody();
str += "</table>";
calcontainer.innerHTML = str;
//ie6下插入iframe
if (YAHOO.env.ua.ie == 6) {
var iframe = document.createElement('iframe');
$D.setStyle(iframe, 'z-index', 1);
$D.setStyle(iframe, 'position', 'absolute');
$D.setStyle(iframe, 'border', 'none');
$D.setStyle(iframe, 'margin', '0');
$D.setStyle(iframe, 'padding', '0');
$D.setStyle(iframe, 'width', '100%');
$D.setStyle(iframe, 'height', '100%');
$D.setStyle(iframe, 'top', '0');
$D.setStyle(iframe, 'left', '0');
$D.setStyle(iframe, 'opacity', '0');
$D.setAttribute(iframe, 'src', 'javascript:false;');
calcontainer.appendChild(iframe);
}
$E.addListener($('calnavleft'), "click", this.showPrevM, this, true);
$E.addListener($('calnavright'), "click", this.showNextM, this, true);
for (i = 0; i < 42; i++) {
var calDayElment = $("calDay" + i);
$E.addListener(calDayElment, "click", this.onClickDay, this, true);
$E.addListener(calDayElment, "mouseover", this.onmMouseover, this, true);
$E.addListener(calDayElment, "mouseout", this.onmMouseout, this, true);
this.defConfig.DayArr[i] = calDayElment;
}
$E.addListener($('clearbutton'), "click", this.clearData, this, true);
this.defConfig.calYearsel = $('calYearsel');
this.defConfig.calMonthsel = $('calMonthsel');
this.defConfig.calbottom = $('calbottom');
},
clearData: function(){
this.defConfig.targetObj.value = "";
},
onClickDay: function(evt) {
evt = evt || window.event;
var eventElement = evt.target || evt.srcElement;
var DateStr = "";
var iYear = eventElement.iYear;
var iMonth = eventElement.iMonth;
var iday = eventElement.innerHTML;

if(iMonth<10){
	iMonth="0"+iMonth;
}
if(iday<10){
	iday="0"+iday;
}

DateStr = iYear + "-" + iMonth + "-" + iday;

if (eventElement.iMonth == 0) {
this.defConfig.targetObj.value = "";
}
else {
this.defConfig.targetObj.value = DateStr;
}
$D.setStyle('fd-container', 'display', 'none');
},
showPrevM: function() {
var iYear = this.defConfig.calYearsel.options[this.defConfig.calYearsel.selectedIndex].value;
var iMonth = this.defConfig.calMonthsel.options[this.defConfig.calMonthsel.selectedIndex].value;
iMonth--;
if (iMonth == 0) {
iMonth = 12;
iYear--;
this.defConfig.calMonthsel.selectedIndex = 11;
this.defConfig.calYearsel.selectedIndex--;
if (this.defConfig.calYearsel.selectedIndex == 0) {
var tempYear = iYear - 6;
for (var i = 0; i < 12; i++) {
this.defConfig.calYearsel.options[i].text = tempYear + "年";
this.defConfig.calYearsel.options[i].value = tempYear;
tempYear++;
}
this.defConfig.calYearsel.selectedIndex = 6;
}
}
else {
this.defConfig.calMonthsel.selectedIndex--;
}
var dateStr = iYear + "-" + iMonth + "-01";
this.setData(dateStr);
},
showNextM: function() {
var iYear = this.defConfig.calYearsel.options[this.defConfig.calYearsel.selectedIndex].value;
var iMonth = this.defConfig.calMonthsel.options[this.defConfig.calMonthsel.selectedIndex].value;
iMonth++;
if (iMonth == 13) {
iMonth = 1;
iYear++;
this.defConfig.calMonthsel.selectedIndex = 0;
this.defConfig.calYearsel.selectedIndex++;
if (this.defConfig.calYearsel.selectedIndex == 11) {
var tempYear = iYear - 6;
for (var i = 0; i < 12; i++) {
this.defConfig.calYearsel.options[i].text = tempYear + "年";
this.defConfig.calYearsel.options[i].value = tempYear;
tempYear++;
}
this.defConfig.calYearsel.selectedIndex = 6;
}
}
else {
this.defConfig.calMonthsel.selectedIndex++;
}
var dateStr = iYear + "-" + iMonth + "-01";
this.setData(dateStr);
},
onSelectChange: function() {
var iYear = this.defConfig.calYearsel.options[this.defConfig.calYearsel.selectedIndex].value;
var iMonth = this.defConfig.calMonthsel.options[this.defConfig.calMonthsel.selectedIndex].value;
var dateStr = iYear + "-" + iMonth + "-01";
this.setData(dateStr);
//到年份下拉列表的的第(最后)一年时，重新获取下拉列表的年份，以显示以前(后)的年份
if (this.defConfig.calYearsel.selectedIndex == 0 || this.defConfig.calYearsel.selectedIndex == 11) {
var tempYear = iYear - 6;
for (var i = 0; i < 12; i++) {
this.defConfig.calYearsel.options[i].text = tempYear + "年";
this.defConfig.calYearsel.options[i].value = tempYear;
tempYear++;
}
this.defConfig.calYearsel.selectedIndex = 6;
}
},
onmMouseover: function(evt) {
evt = evt || window.event;
var eventElement = evt.target || evt.srcElement;
this.setClass(eventElement, 'calMouseOver');
},
onmMouseout: function(evt) {
evt = evt || window.event;
var eventElement = evt.target || evt.srcElement;
this.setClass(eventElement, eventElement.oClass);
},
setHead: function(year, month) {
//var Myowner = this;
var calMonthStr = "";
var calYearStr = "";
//IE 不支持select的innerHTML方法，所以用如下方法代替
if (this.defConfig.calYearsel.options.length == 0) {
for (var i = year - 6; i < year + 6; i++) {
this.defConfig.calYearsel.options[this.defConfig.calYearsel.options.length] = new Option(i + "年", i);
}
this.defConfig.calYearsel.selectedIndex = 6;
for (i = 1; i <= 12; i++) {
this.defConfig.calMonthsel.options[this.defConfig.calMonthsel.options.length] = new Option(i + "月", i);
}
this.defConfig.calMonthsel.selectedIndex = month;
$E.addListener(this.defConfig.calMonthsel, "change", this.onSelectChange, this, true);
$E.addListener(this.defConfig.calYearsel, "change", this.onSelectChange, this, true);
/*两种不同的调用方法
$E.addListener(this.defConfig.calMonthsel,"change",onSelectChange);
function onSelectChange()
{
var iYear = this.defConfig.calYearsel.options[this.defConfig.calYearsel.selectedIndex].value;
var iMonth = this.defConfig.calMonthsel.options[this.defConfig.calMonthsel.selectedIndex].value;
var dateStr = iYear + "-" + iMonth + "-01";
Myowner.setData(dateStr);
}*/
}
else {
for (var i = year - 6; i < year + 6; i++) {
this.defConfig.calYearsel.options[i - year + 6].text = i + "年";
this.defConfig.calYearsel.options[i - year + 6].value = i;
}
this.defConfig.calYearsel.selectedIndex = 6;
this.defConfig.calMonthsel.selectedIndex = month;
}
if (this.defConfig.calbottom.innerHTML == "") {
var iMonth = this.defConfig.calMonth + 1;
this.defConfig.calbottom.innerHTML = "今天:" + this.defConfig.calYear + "年" + iMonth + "月" + this.defConfig.calDay + "日";
}
},
setClass: function(obj, classStr) {
if ($D.hasClass(obj, 'caltoday') == true) {
$D.removeClass(obj, 'caltoday');
}
if ($D.hasClass(obj, 'calthisM') == true) {
$D.removeClass(obj, 'calthisM');
}
if ($D.hasClass(obj, 'calNthisM') == true) {
$D.removeClass(obj, 'calNthisM');
}
if ($D.hasClass(obj, 'calMouseOver') == true) {
$D.removeClass(obj, 'calMouseOver');
}
if ($D.hasClass(obj, 'calneedremove') == true) {
$D.removeClass(obj, 'calneedremove');
}
if ($D.hasClass(obj, 'calsettedday') == true) {
$D.removeClass(obj, 'calsettedday');
}
$D.addClass(obj, classStr);
},
setData: function(inputDate) {
var DataArr = inputDate.split('-');
var _date = new Date(DataArr[0], DataArr[1] - 1, DataArr[2]);
if (isNaN(_date)) {
_date = new Date();
}
this.defConfig._date = _date;
var settedDataArr = this.defConfig.targetObj.value.split('-');
var settedYear = -1;
var settedMonth = -1;
var settedDay = -1;
//如果输入框有符合格式的日期，记录这个日期
if(settedDataArr.length == 3)
{
settedYear = settedDataArr[0];
settedMonth = settedDataArr[1]-1;
settedDay = settedDataArr[2];
}
var calYear = _date.getFullYear();
var calMonth = _date.getMonth();
var calDay = 1;
this.defConfig.calYear = new Date().getFullYear(); //记录当天的年份
this.defConfig.calMonth = new Date().getMonth(); //记录当天的月份(月份0表示1月，以此类推)
this.defConfig.calDay = new Date().getDate(); //记录当天的日期
this.setHead(calYear, calMonth);
//new Date()设置日期，3个参数，分别是年月日，其中月份是从0-11，Date().getDay()则得到这一天是星期几，0表示周日，1-6对应周一至周六
var startDay = new Date(calYear, calMonth, 1).getDay(); //算出当月的第一天是星期几
var prevMYear = calMonth == 0 ? calYear - 1 : calYear; //日历上个月的年份，如果本月是一月，那么年份减一，否则不变
var prevMonth = calMonth == 0 ? 11 : calMonth - 1; //日历上个月的月份，如果本月是一月，那么月份变成十月，否则减一
var prevMDay = this.defConfig.MonthDays[prevMonth]; //prevMDay表示上个月的天数
var nextMYear = calMonth == 11 ? calYear + 1 : calYear; //日历上下月的年份，如果本月是十二月，那么年份加一，否则不变
var nextMonth = calMonth == 11 ? 0 : calMonth + 1; //日历下个月的月份，如果本月是十二月，那么月份变成一月，否则加一
var nextMDay = 1; //下个月要显示的日期从1号开始
//如果有日期范围，考虑日期范围
var sbeginDatestr = this.defConfig.targetObj.getAttribute('beginDate');
var sendDatestr = this.defConfig.targetObj.getAttribute('endDate');
var iBeginYear = -1;
var iBeginMonth = -1;
var iBeginDay = -1;
var iendYear = -1;
var iendMonth = -1;
var iendDay = -1;
var needremove = false;
if(sbeginDatestr) {
iBeginYear = sbeginDatestr.split('-')[0];
iBeginMonth = sbeginDatestr.split('-')[1];
iBeginDay = sbeginDatestr.split('-')[2];
}
if(sendDatestr) {
iendYear = sendDatestr.split('-')[0];
iendMonth = sendDatestr.split('-')[1];
iendDay = sendDatestr.split('-')[2];
}
if (prevMonth == 1) {
prevMDay = ((prevMYear % 4 == 0) && (prevMYear % 100 != 0) || (prevMYear % 400 == 0)) ? 29 : 28;
} //这一段算上个月的日期，如果是二月，则需要判断是否是闰月
prevMDay -= startDay - 1; //计算上个月从几号开始显示在本月,prevMDay = prevMDay-(startDay - 1)
this.defConfig.MonthDays[1] = ((calYear % 4 == 0) && (calYear % 100 != 0) || (calYear % 400 == 0)) ? 29 : 28; //判断当年的二月是否是闰月
for (i = 0; i < 42; i++) {
//$E.addListener(this.defConfig.DayArr[i], "click", this.onClickDay, this, true);
//var calDayElment = $("calDay" + i);
//$E.addListener(calDayElment,"click",this.onClickDay,this,true);
//$E.addListener(calDayElment,"mouseover",this.onmMouseover,this,true);
//$E.addListener(calDayElment,"mouseout",this.onmMouseout,this,true);
if (i < startDay) {
//获取上一个月的日期
if (this.defConfig.showMoreDay) {
var prevMDate = new Date(prevMYear, prevMonth, prevMDay);
this.defConfig.DayArr[i].innerHTML = prevMDay;
this.defConfig.DayArr[i].iMonth = prevMonth + 1;
this.defConfig.DayArr[i].iYear = prevMYear;
this.defConfig.DayArr[i].oClass = 'calNthisM';
this.setClass(this.defConfig.DayArr[i], 'calNthisM');
prevMDay++;
}
else {
this.defConfig.DayArr[i].innerHTML = "";
this.defConfig.DayArr[i].title = "";
this.defConfig.DayArr[i].iMonth = 0;
}
}
else if (i >= new Date(calYear, calMonth, 1).getDay() && calDay <= this.defConfig.MonthDays[calMonth]) {
//获取本月的日期
this.defConfig.DayArr[i].innerHTML = calDay;
this.defConfig.DayArr[i].iMonth = calMonth + 1;
this.defConfig.DayArr[i].iYear = calYear;
if (calDay == this.defConfig.calDay && calMonth == this.defConfig.calMonth && calYear == this.defConfig.calYear) {
this.defConfig.DayArr[i].oClass = 'caltoday';
this.setClass(this.defConfig.DayArr[i], 'caltoday');
}
else {
//设置当前选定日期的颜色
if(calDay == settedDay && calMonth == settedMonth && calYear == settedYear)
{
this.defConfig.DayArr[i].oClass = 'calsettedday';
this.setClass(this.defConfig.DayArr[i], 'calsettedday');
}
else
{
this.defConfig.DayArr[i].oClass = 'calthisM';
this.setClass(this.defConfig.DayArr[i], 'calthisM');
}
}
calDay++;
}
else {
//获取下一个月的日期
if (this.defConfig.showMoreDay) {
var nextMDate = new Date(nextMYear, nextMonth, 1);
this.defConfig.DayArr[i].innerHTML = nextMDay;
nextMDay++;
this.defConfig.DayArr[i].iMonth = nextMonth + 1;
this.defConfig.DayArr[i].iYear = nextMYear;
this.defConfig.DayArr[i].oClass = 'calNthisM';
this.setClass(this.defConfig.DayArr[i], 'calNthisM');
}
else {
this.defConfig.DayArr[i].innerHTML = "";
this.defConfig.DayArr[i].title = "";
this.defConfig.DayArr[i].iMonth = 0;
}
}
needremove = false;
if(iBeginYear > 0) {
if(this.defConfig.DayArr[i].iYear < iBeginYear) {
needremove = true;
}
else if(this.defConfig.DayArr[i].iYear == iBeginYear && this.defConfig.DayArr[i].iMonth < iBeginMonth) {
needremove = true;
}
else if(this.defConfig.DayArr[i].iYear == iBeginYear && this.defConfig.DayArr[i].iMonth == iBeginMonth && parseInt(this.defConfig.DayArr[i].innerHTML) < iBeginDay) {
needremove = true;
}
if(needremove == true)
{
$E.removeListener(this.defConfig.DayArr[i],"click");
$D.setStyle(this.defConfig.DayArr[i],"cursor","default");
//$D.setStyle(this.defConfig.DayArr[i],"visibility","hidden");
this.setClass(this.defConfig.DayArr[i], 'calneedremove');
this.defConfig.DayArr[i].oClass = 'calneedremove';
}
else {
$E.addListener(this.defConfig.DayArr[i], "click", this.onClickDay, this, true);
$D.setStyle(this.defConfig.DayArr[i],"cursor","pointer");
//$D.setStyle(this.defConfig.DayArr[i],"visibility","");
}
}
if(iendYear > 0) {
if(this.defConfig.DayArr[i].iYear > iendYear) {
needremove = true;
}
else if(this.defConfig.DayArr[i].iYear == iendYear && this.defConfig.DayArr[i].iMonth > iendMonth) {
needremove = true;
}
else if(this.defConfig.DayArr[i].iYear == iendYear && this.defConfig.DayArr[i].iMonth == iendMonth && parseInt(this.defConfig.DayArr[i].innerHTML) > iendDay) {
needremove = true;
}
if(needremove == true)
{
$E.removeListener(this.defConfig.DayArr[i],"click");
$D.setStyle(this.defConfig.DayArr[i],"cursor","default");
//$D.setStyle(this.defConfig.DayArr[i],"visibility","hidden");
this.setClass(this.defConfig.DayArr[i], 'calneedremove');
this.defConfig.DayArr[i].oClass = 'calneedremove';
}
else {
$E.addListener(this.defConfig.DayArr[i], "click", this.onClickDay, this, true);
$D.setStyle(this.defConfig.DayArr[i],"cursor","pointer");
//$D.setStyle(this.defConfig.DayArr[i],"visibility","");
}
}
}
},
show: function() {
/*evt = evt || window.event;
var eventElement= evt.target || evt.srcElement;*/
if (arguments.length > 3 || arguments.length == 0) {
alert("对不起！参数个数不对！");
return;
}
/*if(evt.type == "button" && $D.getStyle('fd-container', 'display') != "none")
{
$D.setStyle('fd-container', 'display','none');
return;
}
else if(evt.type == "text")
{
$D.setStyle('fd-container', 'display','none');
}*/
var showObj = null;
var eventObj = null;
var initValue = null;
var _date = null;
for (var i = 0; i < arguments.length; i++) {
if (typeof (arguments[i]) == "object" && arguments[i].type == "text")//text类型表示 input type="text"，这个类型用于点击按钮才显示的情况
{
showObj = arguments[i]; //showObj表示点击按钮后显示日历的那个输入框
}
else if (typeof (arguments[i]) == "object") {
eventObj = arguments[i]; //eventObj表示触发onfocus事件的输入框
}
else if (typeof (arguments[i]) == "string") {
initValue = arguments[i];
}
}
//如果是同一个输入框，并且已经显示，则再次点击不再响应
if(this.defConfig.targetObj == showObj && $D.getStyle('fd-container', 'display') != "none")
{
return;
}
_date = showObj.value;
if (_date == "" && initValue)    //如果输入框中没有时间，则以调用函数时设定的初始时间为显示时间
{
_date = initValue;
}
this.defConfig.inputObj = eventObj;
this.defConfig.targetObj = showObj;
$E.addListener(document, "click", this.hide, this, true);
this.setData(_date);
var styletop = $D.getXY(showObj)[1] + showObj.offsetHeight + this.defConfig.YOffset;
var styleleft = $D.getXY(showObj)[0] + this.defConfig.XOffset;
//var css = '#fd-container{top:'+styletop+'px;left:'+styleleft+'px}';
//var myCss = new $Y.StyleSheet(css);
$D.setStyle('fd-container', 'top', styletop + 'px');
$D.setStyle('fd-container', 'left', styleleft + 'px');
$D.setStyle('fd-container', 'display', '');
if (this.defConfig.Dragable == true) {
new YAHOO.util.DD('fd-container');
}
else {
var temp = $D.getElementsByClassName('candrag').length;
for (i = 0; i < temp; i++) {
$D.removeClass($D.getElementsByClassName('candrag')[0], 'candrag');
}
}
},
hide: function(evt) {
evt = evt || window.event;
var eventElement = evt.target || evt.srcElement;
var isInCal = false;
for (var tempNode = eventElement; tempNode != null; tempNode = tempNode.parentNode) {
if (tempNode.id == 'fd-container') {
isInCal = true;
break;
}
}
if (isInCal == false && eventElement != this.defConfig.inputObj && eventElement != this.defConfig.targetObj) {
$D.setStyle('fd-container', 'display', 'none');
}
}
}
/**
* 添加静态方法init初始化
* @method init
* @param {Object} container 外层容器
* @param {Object} config 配置参数
*/
FD.widget.Calender.init = function(config) {
return new FD.widget.Calender(config);
};
/// <reference path="../../core/fdev.js"/>
/// <reference path="../../core/yui/animation-min.js"/>
/*
* @fileoverview 遮罩浮层效果
* @author Denis<danxia.shidx@alibaba-inc.com>
* @version 1.0.0
*/
; (function(w) {
/**
* 假如是全屏遮罩el传入window 局部遮罩el传入需要被遮罩的对象
* @method FD.widget.block
* @param {HTMLElement | Array} el HTMLElement(s)
* @param {Object} opts 配置
*/
w.block = function(el, opts) {
if (el != window) {
if (el.nodeType) el = [el];
for (var i = 0; i < el.length; i++) {
if ($D.getStyle(el[i], 'position') == 'static')
$D.setStyle(el[i], 'position', 'relative');
if (YAHOO.env.ua.ie > 0)
$D.setStyle(el[i], 'zoom', '1');
install(el[i], opts);
}
} else install(window, opts);
};
/**
* 取消遮罩
* @method FD.widget.block
* @param {HTMLElement | Array} el HTMLElement(s)
* @param {Object} opts 配置
*/
w.unblock = function(el, opts) {
if (el != window) {
if (el.nodeType) el = [el];
for (var i = 0; i < el.length; i++) {
remove(el[i], opts);
}
} else
remove(window, opts);
};
w.block.defaults = {
// 默认显示的信息
message: '<h1>请稍后...</h1>',
// styles for the message when blocking; if you wish to disable
// these and use an external stylesheet then do this in your code:
// $.blockUI.defaults.css = {};
css: {},
// styles for the overlay
overlayCSS: {
backgroundColor: '#fff',
opacity: .3
},
// z-index for the blocking overlay
baseZ: 1000,
// set these to true to have the message automatically centered
centerX: true, // <-- only effects element blocking (page block controlled via css above) 在局部遮罩效果中有效
centerY: true,
fixed: true,
// allow body element to be stetched in ie6; this makes blocking look better
// on "short" pages.  disable if you wish to prevent changes to the body height 在ie6中 “短”页面更好地呈现遮罩层
allowBodyStretch: true,
// be default blockUI will supress tab navigation from leaving blocking content;
constrainTabKey: true,
// fadeIn time in millis; set to 0 to disable fadeIn on block
fadeIn: 0,
// fadeOut time in millis; set to 0 to disable fadeOut on unblock
fadeOut: 0,
// time in millis to wait before auto-unblocking; set to 0 to disable auto-unblock 自动取消遮罩的时间 0为不自动取消
timeout: 0,
// disable if you don't want to show the overlay 是否显示遮罩层
showOverlay: true,
// if true, focus will be placed in the first available input field when
// page blocking 聚焦在浮层内第一个表单元素上
focusInput: true,
// suppresses the use of overlay styles on FF/Linux (due to performance issues with opacity)
applyPlatformOpacityRules: true,
// callback method invoked when unblocking has completed; the callback is
// passed the element that has been unblocked (which is the window object for page
// blocks) and the options that were passed to the unblock call:
//     onUnblock(element, options) 取消遮罩后触发事件
onUnblock: null
};
//缓存状态
w.block.cache = {};
var ie6 = YAHOO.env.ua.ie == 6, pageBlock = null, pageBlockEls = [];
function install(el, opts) {
var full = (el == window);
var msg = opts && opts.message !== undefined ? opts.message : undefined;
opts = FD.common.applyIf(opts || {}, w.block.defaults);
opts.overlayCSS = FD.common.applyIf(opts.overlayCSS || {}, w.block.defaults.overlayCSS);
var css = FD.common.applyIf(opts.css || {}, w.block.defaults.css);
msg = msg === undefined ? opts.message : msg;
// remove the current block (if there is one)
if (full && pageBlock)
remove(window, { fadeOut: 0 });
// if an existing element is being used as the blocking content then we capture
// its current place in the DOM (and current display style) so we can restore
// it when we unblock
if (msg && typeof msg != 'string' && msg.parentNode) {
var node = msg;
var data = {};
w.block.cache.history = data;
data.el = node;
data.parent = node.parentNode;
data.display = node.style.display;
data.position = node.style.position;
if (data.parent)
data.parent.removeChild(node);
}
var z = opts.baseZ;
// blockUI uses 3 layers for blocking, for simplicity they are all used on every platform;
// layer1 is the iframe layer which is used to supress bleed through of underlying content
// layer2 is the overlay layer which has opacity and a wait cursor
// layer3 is the message content that is displayed while blocking
var lyr1 = (YAHOO.env.ua.ie > 0) ? (function() {
var iframe = document.createElement('iframe');
$D.addClass(iframe, 'blockUI');
$D.setStyle(iframe, 'z-index', z++);
$D.setStyle(iframe, 'position', 'absolute');
$D.setStyle(iframe, 'border', 'none');
$D.setStyle(iframe, 'margin', '0');
$D.setStyle(iframe, 'padding', '0');
$D.setStyle(iframe, 'width', '100%');
$D.setStyle(iframe, 'height', '100%');
$D.setStyle(iframe, 'top', '0');
$D.setStyle(iframe, 'left', '0');
$D.setStyle(iframe, 'display', 'none');
$D.setAttribute(iframe, 'src', 'javascript:false;');
return iframe;
})() : (function() {
var div = document.createElement('div');
$D.addClass(div, 'blockUI');
$D.setStyle(div, 'display', 'none');
return div;
})();
var lyr2 = (function() {
var div = document.createElement('div');
$D.setAttribute(div, 'class', 'blockUI blockOverlay');
$D.setStyle(div, 'z-index', z++);
$D.setStyle(div, 'display', 'none');
$D.setStyle(div, 'border', 'none');
$D.setStyle(div, 'margin', '0');
$D.setStyle(div, 'padding', '0');
$D.setStyle(div, 'width', '100%');
$D.setStyle(div, 'height', '100%');
$D.setStyle(div, 'top', '0');
$D.setStyle(div, 'left', '0');
return div;
})();
var lyr3 = full ? (function() {
var div = document.createElement('div');
$D.setAttribute(div, 'class', 'blockUI blockMsg blockPage');
$D.setStyle(div, 'z-index', z);
$D.setStyle(div, 'display', 'none');
$D.setStyle(div, 'position', opts.fixed ? 'fixed' : 'absolute');
return div;
})() : (function() {
var div = document.createElement('div');
$D.setAttribute(div, 'class', 'blockUI blockMsg blockElement');
$D.setStyle(div, 'z-index', z);
$D.setStyle(div, 'display', 'none');
$D.setStyle(div, 'position', 'absolute');
return div;
})();
// if we have a message, style it
if (msg)
for (p in css) $D.setStyle(lyr3, p, css[p]);
// style the overlay
if (!opts.applyPlatformOpacityRules || !(YAHOO.env.ua.gecko > 0 && /Linux/.test(navigator.platform)))
for (p in opts.overlayCSS) $D.setStyle(lyr2, p, opts.overlayCSS[p]);
$D.setStyle(lyr2, 'position', full ? 'fixed' : 'absolute');
// make iframe layer transparent in IE
if (YAHOO.env.ua.ie > 0)
$D.setStyle(lyr1, 'opacity', 0);
var scope = full ? document.body : el;
scope.appendChild(lyr1);
scope.appendChild(lyr2);
scope.appendChild(lyr3);
// ie7 must use absolute positioning in quirks mode and to account for activex issues (when scrolling)
var expr = YAHOO.env.ua.ie > 0 && YAHOO.env.ua.ie < 8 && (function() {
return scope.getElementsByTagName('object').length + scope.getElementsByTagName('embed').length > 0
})();
if (ie6 || expr && lyr3.style.setExpression) {
// give body 100% height
if (full && opts.allowBodyStretch)
$D.setStyle([document.getElementsByTagName('html')[0], document.body], 'height', '100%');
// fix ie6 issue when blocked element has a border width
if (ie6 && !full) {
var t = sz(el, 'borderTopWidth'), l = sz(el, 'borderLeftWidth');
var fixT = t ? '(0 - ' + t + ')' : 0;
var fixL = l ? '(0 - ' + l + ')' : 0;
}
// simulate fixed position
$D.batch([lyr1, lyr2, lyr3], function(o) {
var s = o.style;
s.position = 'absolute';
if (o != lyr3) {
full ? s.setExpression('height', 'Math.max(document.body.scrollHeight, document.body.offsetHeight) + "px"')
: s.setExpression('height', 'this.parentNode.offsetHeight + "px"');
full ? s.setExpression('width', 'document.documentElement.clientWidth || document.body.clientWidth + "px"')
: s.setExpression('width', 'this.parentNode.offsetWidth + "px"');
if (fixL) s.setExpression('left', fixL);
if (fixT) s.setExpression('top', fixT);
}
//在全屏不fixed的情况下
else if (!opts.fixed && full) {
s.top = (opts.css && opts.css.top) ? parseInt(opts.css.top) : 0;
} else if (opts.centerY && !full) {
s.marginTop = 0;
} else if (full) {
var top = (opts.css && opts.css.top) ? parseInt(opts.css.top) : 0;
var expression = '((blah = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop) + ' + top + ') + "px"';
s.setExpression('top', expression);
}
});
}
// show the message
if (msg) {
if (typeof msg == 'string')
lyr3.innerHTML = msg;
else lyr3.appendChild(msg);
if (msg && msg.nodeType)
$D.setStyle(msg, 'display', 'block');
}
if (YAHOO.env.ua.ie > 0 && opts.showOverlay)
$D.setStyle(lyr1, 'display', 'block');
if (opts.fadeIn) {
if (opts.showOverlay) {
$D.setStyle(lyr2, 'opacity', 0);
$D.setStyle(lyr2, 'display', 'block');
var fi1 = new $Y.Anim(lyr2, { opacity: { to: opts.overlayCSS.opacity} }, opts.fadeIn);
fi1.animate();
}
if (msg) {
$D.setStyle(lyr3, 'opacity', 0);
$D.setStyle(lyr3, 'display', 'block');
var fi2 = new $Y.Anim(lyr3, { opacity: { to: 1} }, opts.fadeIn);
fi2.animate();
}
}
else {
if (opts.showOverlay)
$D.setStyle(lyr2, 'display', 'block');
if (msg)
$D.setStyle(lyr3, 'display', 'block');
}
// bind key and mouse events
bind(1, el, opts);
if (full) {
pageBlock = lyr3;
pageBlockEls = $$('input,textarea', pageBlock).filter(function(elem) { return (elem.disabled === false && elem.type !== 'hidden' && (elem.offsetWidth > 0 || elem.offsetHeight > 0)) ? true : false; });
pageBlockEls = pageBlockEls.filter(function(elem) { return (elem.disabled === false && elem.type !== 'hidden' && (elem.offsetWidth > 0 || elem.offsetHeight > 0)) ? true : false; });
if (opts.focusInput)
setTimeout(focus, 20);
}
else
center(lyr3, opts.centerX, opts.centerY);
if (opts.timeout) {
// auto-unblock
setTimeout(function() {
full ? w.unblock(window, opts) : w.unblock(el, opts);
}, opts.timeout);
}
};
// move blocking element back into the DOM where it started
function reset(els, data, opts, el) {
for (i in els) {
if (els[i].parentNode)
els[i].parentNode.removeChild(els[i]);
}
if (data && data.el) {
data.el.style.display = data.display;
data.el.style.position = data.position;
if (data.parent)
data.parent.appendChild(data.el);
w.block.cache.history = null;
}
if (typeof opts.onUnblock == 'function')
opts.onUnblock(el, opts);
};
// event handler to suppress keyboard/mouse events when blocking
function handler(e, obj) {
// allow tab navigation (conditionally)
if (e.keyCode && e.keyCode == 9) {
if (pageBlock && obj.constrainTabKey) {
var els = pageBlockEls;
if (els.indexOf($E.getTarget(e)) < 0) $E.preventDefault(e);
var fwd = !e.shiftKey && e.target == els[els.length - 1];
var back = e.shiftKey && e.target == els[0];
if (fwd || back) {
setTimeout(function() { focus(back) }, 10);
return false;
}
}
}
// allow events within the message content
var cur = e.target || e.srcElement;
var dvMsg = $D.getAncestorByClassName(cur, 'blockMsg');
if (dvMsg != null && dvMsg.tagName.toLowerCase() == 'div') return true;
// allow events for content that is not being blocked
return (function() {
var divs = document.getElementsByTagName('div');
for (div in divs) {
if ($D.hasClass(div, 'blockUI')) return false;
}
return true;
})();
};
// bind/unbind the handler
function bind(b, el, opts) {
var full = el == window;
// don't bother unbinding if there is nothing to unbind
if (!b && (full && !pageBlock || !full && !w.block.cache.isBlocked))
return;
if (!full)
w.block.cache.isBlocked = b;
if (b && !opts.showOverlay) // don't prevent events when overlay not in use
return;
// bind anchors and inputs for mouse and key events
var events = 'mousedown mouseup keydown keypress'.split(' ');
for (i in events)
b ? $E.addListener(document, events[i], handler, opts) : $E.removeListener(document, events[i], handler);
// former impl...
//    var $e = $('a,:input');
//    b ? $e.bind(events, opts, handler) : $e.unbind(events, handler);
};
// remove the block
function remove(el, opts) {
var full = el == window;
var data = w.block.cache.history;
opts = FD.common.applyIf(opts || {}, w.block.defaults);
bind(0, el, opts); // unbind events
var els = full ? $$('.blockUI', document.body) : $$('.blockUI', el);
if (full)
pageBlock = pageBlockEls = null;
if (opts.fadeOut) {
for (var i = 0; i < els.length; i++) {
var fo = new $Y.Anim(els[i], { opacity: { to: 0} }, opts.fadeOut);
if (i == els.length - 1)
fo.onComplete.subscribe(function() {
reset(els, data, opts, el);
});
fo.animate();
}
//setTimeout(function() { reset(els, data, opts, el); }, opts.fadeOut);
}
else
reset(els, data, opts, el);
};
function focus(back) {
if (!pageBlockEls)
return;
var e = pageBlockEls[back === true ? pageBlockEls.length - 1 : 0];
if (e)
try { e.focus(); } catch (e) { }
};
function center(el, x, y) {
var p = el.parentNode, s = el.style;
var l = ((p.offsetWidth - el.offsetWidth) / 2) - sz(p, 'borderLeftWidth');
var t = ((p.offsetHeight - el.offsetHeight) / 2) - sz(p, 'borderTopWidth');
if (x) s.left = l > 0 ? (l + 'px') : '0';
if (y) s.top = t > 0 ? (t + 'px') : '0';
};
function sz(el, p) {
return parseInt($D.getStyle(el, p)) || 0;
};
})(FD.widget);
/// <reference path="../../core/fdev.js" />
/*
* @fileoverview 该文件封装了Alitalk初始化功能，构建一个组群实例方法为：FD.widget.Alitalk(...)，组件需要用到online这个全局变量，谨防冲突。
* @author Denis<danxia.shidx@alibaba-inc.com>
* @link http://page.china.alibaba.com/shtml/alitalk/alitalk-demo.html
* @version 2.0.1
*/
var online=null;
(function(w) {
/*
* 实例化Alitalk的入口.
* @param {HTMLElement|Array} Dom单一元素或元素组
* @param {object} opts 配置参数
*/
var Alitalk=function(els,opts) {
this.init(els,opts);
},
//是否为IE浏览器
isIE=!!(YAHOO.env.ua.ie),
//保存贸易通版本号，目前为5或6
version,
//是否安装贸易通或阿里旺旺
isInstalled=(function() {
var vers={
'aliimx.wangwangx': 6,
'Ali_Check.InfoCheck': 5
};
for(var p in vers) {
try {
new ActiveXObject(p)
version=vers[p];
return true;
} catch(e) { }
}
version=0;
return false;
})(),
//弹出阿里旺旺下载页
getAlitalk=function() {
window.open('http://www.9dyou.com','_blank');
},
//自动登陆
autoLogin=function(id) {
if(version==5) {
location.href='alitalk:'
} else {
id=id?'?uid='+id:'';
window.location.href='aliim:login'+id;
}
},
//默认配置
defaults={
type: 2,
//数组对应的类型依次为自定义、按钮和图标
cls: [{},{ base: 'btnAlitalk',on: 'btn-on',off: 'btn-off',mb: 'btn-mb' },{ base: 'iconAlitalk',on: 'icon-on',off: 'icon-off',mb: 'icon-mb'}],
siteID: 'cnalichn',
//是否请求用户在线状态
remote: true,
//实例化过程中是否开启自动登陆
autoLogin: false,
prop: ''
},
//回调函数
success=function(ali) {
for(var i=0;i<ali.els.length;i++) {
if(ali.els[i].opt.remote) { //对没有指定remote为false的判断在线状态并加上相应样式
ali.els[i].online=online[i]; //把在线状态保存到元素属性以便于后期调用
$D.addClass(ali.els[i],ali.els[i].opt.cls[ali.els[i].opt.type].base); //上基样式
switch(online[i]) {
case 0:
case 2:
case 6:
default: //不在线
$D.addClass(ali.els[i],ali.els[i].opt.cls[ali.els[i].opt.type].off);
break;
case 1: //在线
$D.addClass(ali.els[i],ali.els[i].opt.cls[ali.els[i].opt.type].on);
break;
case 4:
case 5: //手机在线
$D.addClass(ali.els[i],ali.els[i].opt.cls[ali.els[i].opt.type].mb);
break;
}
}
}
//重置
online=null;
};
Alitalk.prototype={
/*
* 实例化Alitalk的入口. 这是组件实例唯一暴露在外的方法
* @param {HTMLElement|Array} Dom单一对象或对象组
* @param {object} opts 配置参数
*/
init: function(els,opts) {
//防止多次调用online冲突
if(online!=null) {
setTimeout(function() { new w.Alitalk(els,opts); },Math.random()*1000+1000);
return;
} else {
this.opts=FD.common.applyIf(opts||{},defaults);
if(this.opts.remote) online=[];
}
if(els.nodeType) els=[els];
if(!els.length) return;
this.els=els;
var ids='',that=this;
for(var i=0;i<els.length;i++) {
var o=FD.common.applyIf(eval('('+($D.getAttribute(els[i],'alitalk')||'{}')+')'),this.opts);
if(!o.id||!YAHOO.lang.trim(o.id)) {
els.splice(i,1);
i--;
} else {
els[i].opt=o;
ids+=o.id+';';
}
}
//给按钮注册点击事件
$E.addListener(els,'click',function(e) {
$E.preventDefault(e);
//非ie浏览器不支持
if(!isIE) return;
//静态模式下 设置默认状态为在线
if(!that.opts.remote) this.online=1;
//还没有获取到状态
if(this.online==null) return;
//解析用户id
var userID=eval('('+($D.getAttribute(this,'alitalk')||'{}')+')').id;
switch(version) {
case 0:
default:
getAlitalk(); //下载阿里旺旺
break;
case 5:
window.location='Alitalk:Send'+(this.online==4?'Sms':'IM')+'?'+userID+'&siteid='+that.opts.siteID+'&status='+this.online+that.opts.prop;
break;
case 6:
if(this.online==4) window.location='aliim:smssendmsg?touid=cnalichn'+userID+that.opts.prop;
else window.location='aliim:sendmsg?touid=cnalichn'+userID+'&siteid='+that.opts.siteID+'&status='+this.online+that.opts.prop;
break;
}
});
//从阿里软件获取在线状态
if(this.opts.remote)
YAHOO.util.Get.script('http://amos.im.alisoft.com/muliuserstatus.aw?uids='+ids+'&site='+that.opts.siteID,{ charset: 'gb2312',onSuccess: function() { success(that); },onFailure: function() { online=null; },onTimeout: function() { online=null; } });
//自动登录
if(isIE&&this.opts.autoLogin)
autoLogin();
}
};
/*
* 静态变量及方法
*/
w.Alitalk=Alitalk;
/*
* Alitalk客户端版本
*/
w.Alitalk.version=version;
/*
* 客户端是否安装了Alitalk
*/
w.Alitalk.isInstalled=isInstalled;
/*
* 弹出alitalk下载页面
*/
w.Alitalk.getAlitalk=getAlitalk;
/*
* 自动启动alitalk客户端软件
*/
w.Alitalk.autoLogin=autoLogin;
})(FD.widget);
/**
* resize images equal proportion
*/
function resizeImage(img,w,h) {
//check images exist and width&height >0 and custom width >0
if(img&&img.width&&img.height&&w) {
if(!h) h=w;
if(img.width<w&&img.height<h) return;
var fit=img.width/img.height>=w/h;
img[fit?'width':'height']=fit?w:h;
}
}
function dateCheck() { return false; }
function onSearch() { return false; }
function onModify() { return false; }
FYE.onDOMReady(function() {
//date range
(function() {
var txtDate=FYS('input.ipt-date','orderList'),calRange=FYS('div.sBox a.range','orderList');
var cal=FD.widget.Calender.init({ showMoreDay: true });
FYE.addFocusListener(txtDate,function() {
cal.show(this);
});
FYE.addListener(calRange,'click',function(e) {
$E.preventDefault(e);
var now=new Date(),pre,num;
txtDate[1].value=now.getFullYear()+'-'+(now.getMonth()+1)+'-'+now.getDate();
switch(this.getAttribute('value')) {
case 'd':
num=2;
break;
case 'w':
num=6;
break;
case 'm':
num=29;
break;
}
pre=new Date(now.valueOf()-num*86400000);
txtDate[0].value=pre.getFullYear()+'-'+(pre.getMonth()+1)+'-'+pre.getDate();
});
dateCheck=function() {
if(txtDate[0].value!=''&&txtDate[1].value!='') {
var d1=txtDate[0].value.split('-'),d2=txtDate[1].value.split('-');
if(Date.parse(d1[1]+'/'+d1[2]+'/'+d1[0])>Date.parse(d2[1]+'/'+d2[2]+'/'+d2[0])) {
alert('起始日期不能小于截止日期');return false;
}
}
return true;
}
})();
//more search conditions
(function() {
var btnMore=FYS('div.sBox a.more','orderList'),ipts=FYS('div.sBox input.ipt-text','orderList'),orderno=FYS('input.orderno','orderList'),reg=/^\d*$/,tmp='';
FYE.on(btnMore,'click',function(e) {
FYE.preventDefault(e);
var p=FYD.getNextSibling(FYD.getAncestorByTagName(this,'p'));
if(FYD.hasClass(this,'down')) {
FYD.removeClass(this,'down');
FYD.addClass(this,'up');
FYD.setStyle(p,'display','');
} else {
FYD.removeClass(this,'up');
FYD.addClass(this,'down');
FYD.setStyle(p,'display','none');
}
});
function fun() {
this.value=YAHOO.lang.trim(this.value);
if(this.value) {
if(reg.test(this.value)) {
tmp=this.value;
} else
this.value=tmp;
} else tmp='';
}
FYE.on(orderno,'keyup',fun);
FYE.on(orderno,'blur',fun);
FYE.on(ipts,'blur',function(){this.value=YAHOO.lang.trim(this.value);})
})();
//edit price
(function() {
var btnEdits=FYS('.btnEdit'),cancels=FYS('.edit .right'),submits=FYS('.edit .left'),
c2s=FYS('.edit .c2 input'),c3s=FYS('.edit .c3 input'),reg2=/^\d*(\.\d*)?$/,reg3=/^([-+]?\d*)?(\d\.\d*)?$/,tmp2,tmp3;
function fun2() {
if(this.value) {
if(reg2.test(this.value)) {
tmp2=this.value;
} else
this.value=tmp2;
} else tmp2='';
}
function fun3() {
if(this.value) {
if(reg3.test(this.value)) {
tmp3=this.value;
} else
this.value=tmp3;
} else tmp3='';
}
function calTotal() {
var edit=FYD.getAncestorByClassName(this,'edit'),oldPrice=FYS('.oldPrice',edit)[0],c2=FYS('.c2 input',edit)[0],c3=FYS('.c3 input',edit)[0],c4=FYS('td.c4',edit)[0],fare=FYS('.fare',edit)[0],discount=FYS('.discount',edit)[0],
total=FYS('.total',c4)[0],tip=FYS('.tip',c4)[0],err=FYS('.err',edit),sum;
if(FYD.getStyle(err[0],'display')!='none'||FYD.getStyle(err[1],'display')!='none') return;
fare.value=c2.value;
discount.value=c3.value;
total.innerHTML=sum=(oldPrice.value*1+(c2.value||0)*1+(c3.value||0)*1).toFixed(2);
if(sum<=0) {
FYD.setStyle(tip,'display','none');
FYD.setStyle(err[2],'display','');
} else {
FYD.setStyle(err[2],'display','none');
FYD.setStyle(tip,'display','');
}
}
//block
FYE.on(btnEdits,'click',function(e) {
FYE.preventDefault(e);
var li=FYD.getAncestorByTagName(this,'li'),edit=FYS('.edit',li)[0],xy=FYD.getXY(this),c2=FYS('.c2 input',edit)[0],c3=FYS('.c3 input',edit)[0],oldFare=FYS('.oldFare',edit)[0],oldDiscount=FYS('.oldDiscount',edit)[0],oldPrice=FYS('.oldPrice',edit)[0],total=FYS('.total',edit)[0],tips=FYS('.tip',edit),errs=FYS('.err',edit);
//init
c2.value=tmp2=oldFare.value;
c3.value=tmp3=oldDiscount.value;
FYD.setStyle(tips,'display','');
FYD.setStyle(errs,'display','none');
total.innerHTML=(oldPrice.value*1+(c2.value||0)*1+(c3.value||0)*1).toFixed(2);
FD.widget.block(window,{ message: edit,fixed: false,css: { left: (xy[0]-636)+'px',top: (xy[1]+14)+'px' },overlayCSS: { backgroundColor: '#000'} });
});
FYE.on(cancels,'click',function(e) {
FYE.preventDefault(e);
FD.widget.unblock(window);
});
//valid
FYE.on(c2s,'keyup',fun2);
FYE.on(c3s,'keyup',fun3);
FYE.on(c2s,'blur',function() {
fun2.call(this);
if(!this.value||reg2.test(this.value)) {
var td=FYD.getAncestorByTagName(this,'td'),tip=FYS('.tip',td)[0],err=FYS('.err',td);
this.value=tmp2=(('0'+this.value)*1).toFixed(2);
if(tmp2*1>10000000) {
FYD.setStyle(tip,'display','none');
FYD.setStyle(err,'display','');
} else {
FYD.setStyle(err,'display','none');
FYD.setStyle(tip,'display','');
calTotal.call(this);
}
}
});
FYE.on(c3s,'blur',function() {
fun3.call(this);
if(!this.value||reg3.test(this.value)) {
var td=FYD.getAncestorByTagName(this,'td'),tip=FYS('.tip',td)[0],err=FYS('.err',td);
if(/^[-+]$/.test(this.value)) this.value=0;
this.value=tmp3=(this.value*1).toFixed(2);
if(tmp3*1>100000000||tmp3< -100000000) {
FYD.setStyle(tip,'display','none');
FYD.setStyle(err,'display','');
} else {
FYD.setStyle(err,'display','none');
FYD.setStyle(tip,'display','');
calTotal.call(this);
}
}
});
onModify=function(ctn) {
var err=FYS('.err',ctn);
for(var i=0;i<err.length;i++) {
if(FYD.getStyle(err[i],'display')!='none') return false;
}
return true;
};
})();
onSearch=function() {
if(!dateCheck()) return false;
return false;
};
new FD.widget.Alitalk(FYS('a[alitalk]'));
});
