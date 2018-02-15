
function loadCustProducts(){
	
	var select2 = document.getElementById("customers"); 

	
	$.post("http://localhost:8080/getCust",
		    function(data, status){
		//alert(JSON.stringify(data));
		var prop;
  	  var propCount = 0;

  	  for (prop in data) {
  	    propCount++;
  	  }
		for (var i = 0; i < propCount; i++){
		 
			var id = data[i].custId;
			var value = data[i].custName;
			    select2.innerHTML += "<option id=\""+id+"\" value=\"" + value + "\">" + value + "</option>";
				  
			  
			}
		
			Products();

		    });
}
	
	function Products(){
		var myDiv = document.getElementById("cboxes");
		myDiv.innerHTML = '';
		
		$.post("http://localhost:8080/getProd",
			    function(data, status){
			//alert(JSON.stringify(data));
			var prop;
	  	  var propCount = 0;

	  	  for (prop in data) {
	  	    propCount++;
	  	  }

			var myDiv = document.getElementById("cboxes");
			for (var i = 0; i < propCount; i++){
				var id = data[i].productId;
				var value = data[i].productName;
					   var checkBox = document.createElement("input");
					    var label = document.createElement("label");
					    checkBox.type = "checkbox";
					    checkBox.id = id;
					  checkBox.value = value;
					  checkBox.disabled = "disabled";
					    myDiv.appendChild(checkBox);
					    myDiv.appendChild(label);
					    label.appendChild(document.createTextNode(value));
					    var br = document.createElement("br");
					    myDiv.appendChild(br);
				  
					  
			}

			    });
		
	}
	

function ProductCust(){

	document.getElementById("TotalValue").value = "";
	document.getElementById("AvgValue").value = "";
	var e = document.getElementById("customers");
	var strUser = e.options[e.selectedIndex].id;
	var strUser1 = e.options[e.selectedIndex].text;
	
	if(strUser1 == "Choose a customer"){
		Products();
	}
	else{
	
	var myDiv = document.getElementById("cboxes");
	myDiv.innerHTML = '';
	$.post("http://localhost:8080/getProdCust",
			{
		CustId: strUser
			},
		    function(data, status){
		//alert(JSON.stringify(data));
		var prop;
  	  var propCount = 0;

  	  for (prop in data) {
  	    propCount++;
  	  }

		for (var i = 0; i < propCount; i++){
			var id = data[i].productId;
			var value = data[i].productName;
				   var checkBox = document.createElement("input");
				    var label = document.createElement("label");
				    checkBox.type = "checkbox";
				    checkBox.id = id;
				  checkBox.value = value;
				  checkBox.onclick = function() {totalAverage();};
				    myDiv.appendChild(checkBox);
				    myDiv.appendChild(label);
				    label.appendChild(document.createTextNode(value));
				    var br = document.createElement("br");
				    myDiv.appendChild(br);
			  
				  
		}

		    });

	}
	
	nickNames();
	
}

function addNick(){
	
	var e = document.getElementById("customers");
	var CustId = e.options[e.selectedIndex].id;
	var strUser1 = e.options[e.selectedIndex].text;
	
	var nickname = document.getElementById("AddNickname").value;
	//alert(nickname);
		if(strUser1 == "Choose a customer"){
		 
		alert("Please choose a customer to add nicknames!");
		return false;
	}
	
	if(nickname==""){
		alert("Please enter a nickname!");
		return false;
	}
	
	

	$.post("http://localhost:8080/addNickName",
			{
		CustId: CustId,
		nickname: nickname
			},
		    function(data, status){
				nickNames();				
			
				document.getElementById("AddNickname").value = "";
				
			});
	
	
}


function nickNames(){
	var e = document.getElementById("customers");
	var strUser = e.options[e.selectedIndex].id;
	var strUser1 = e.options[e.selectedIndex].text;
	
	if(strUser1 != "Choose a customer"){
	
		$.post("http://localhost:8080/getNickName",
				{
			CustId: strUser
				},
			    function(data, status){
					var prop;
				  	  var propCount = 0;

				  	  for (prop in data) {
				  	    propCount++;
				  	  }
				  	  var nicks = "";
				  	  
				  	  for(var i=0;i<propCount;i++){
				  		  if(i==propCount -1)nicks = nicks + data[i].nickname;
				  		  else
				  		  nicks = nicks + data[i].nickname + ",";
				  	  }
					
					
				
					document.getElementById("nickNames").value=nicks;
					
				});
		
		
	}
	else{
		
		document.getElementById("nickNames").value="";
	}
	
	
	
}


function totalAverage(){
		
	var prods = [];
	$('#cboxes input:checked').each(function() {
	    prods.push($(this).attr('id'));
	});
	var e = document.getElementById("customers");
	var custId = e.options[e.selectedIndex].id;
	if(prods.length==0)prods = [-10];
	
	$.post("http://localhost:8080/getTotAvg",
			{
		prods: prods,
		custId: custId
			},
		    function(data, status){
				//alert(JSON.stringify(data));
				if(data != ""){
				var total = data[0].total;
				var avg = data[0].avg;
				document.getElementById("TotalValue").value = total;
				document.getElementById("AvgValue").value = avg;
				}else{
					document.getElementById("TotalValue").value = "0";
					document.getElementById("AvgValue").value = "0";
					
				}
		
		
		
		    });
	
	
	
}


function convertArrayOfObjectsToCSV(args) {
    var result, ctr, keys, columnDelimiter, lineDelimiter, data;

    data = args.data || null;
    if (data == null || !data.length) {
        return null;
    }

    columnDelimiter = args.columnDelimiter || ',';
    lineDelimiter = args.lineDelimiter || '\n';

    keys = Object.keys(data[0]);

    result = '';
    result += keys.join(columnDelimiter);
    result += lineDelimiter;

    data.forEach(function(item) {
        ctr = 0;
        keys.forEach(function(key) {
            if (ctr > 0) result += columnDelimiter;

            result += item[key];
            ctr++;
        });
        result += lineDelimiter;
    });

    return result;
}

function downloadCSV(args) {
	
	var e = document.getElementById("customers");
	var strUser = e.options[e.selectedIndex].id;
	var strUser1 = e.options[e.selectedIndex].text;
	
	if(strUser1 == "Choose a customer"){
		alert("Please select Customer!");
		return false;
	}
	
	var prods = [];
	$('#cboxes input:checked').each(function() {
	    prods.push($(this).attr('value'));
	});
	
	
	if(prods.length == 0){
		
		alert("Please select Products Set!");
		return false;
	}
	
	prods = "'" + prods + "'";

	
	var e = document.getElementById("customers");
	var strUser = e.options[e.selectedIndex].id;
	var custList = e.options[e.selectedIndex].text;
	
	var nicks = document.getElementById("nickNames").value;
	nicks = "'" + nicks + "'";
	
	
	var totalamt = document.getElementById("TotalValue").value;
	var avgam = document.getElementById("AvgValue").value;
	
var Data = [

	{
	Customer:custList,
	Nicknames:nicks,
	Products:prods,
	Total:totalamt,
	Avg:avgam
	}
];
    var data, filename, link;

    var csv = convertArrayOfObjectsToCSV({
        data: Data
    });
    if (csv == null) return;

    filename = args.filename || 'export.csv';

    if (!csv.match(/^data:text\/csv/i)) {
        csv = 'data:text/csv;charset=utf-8,' + csv;
    }
    data = encodeURI(csv);

    link = document.createElement('a');
    link.setAttribute('href', data);
    link.setAttribute('download', filename);
    link.click();
}