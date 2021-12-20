var roomStatus;
var content = new XMLHttpRequest();

function getCurrentRoomStatus(){
    content.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var roomStatusObj = JSON.parse(this.responseText);
            roomStatus = roomStatusObj.roomStatus;
            document.getElementById("roomStatus").innerHTML = roomStatus;
        }
    }
    content.open("GET", "/RoomStatus", true);
    content.send();
}

function openRoom(){
    if(roomStatus == "OPEN"){
        alert("The room status is already set to OPEN!");
    }else {
        content.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 201) {
                console.log("RoomStatus changed to open now");
                getCurrentRoomStatus();
            }
        }
        content.open("POST", "/RoomStatus", true);
        content.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        content.send('status=OPEN');
    }
}

function closeRoom(){
    if(roomStatus == "CLOSE"){
        alert("The room status is already set to CLOSE");
    }else {
        content.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 201) {
                console.log("RoomStatus changed to close now");
                getCurrentRoomStatus();
            }
        }
        content.open("POST", "/RoomStatus", true);
        content.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        content.send('status=CLOSE');
    }
}

function getCalanderEntryForCurrentDay(){
    var calender = "";
    content.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var obj = JSON.parse(this.responseText);
            if(obj == ""){
                calender = "No Entries today";
            }else {
                for (var i = 0; i < obj.length; i++) {
                    var jsonString = JSON.stringify(obj[i]);
                    calender += obj[i].date + "<br />" + obj[i].name +
                        "<br /><a><button class='btn btn-success' onclick='openCalenderEntry(" + jsonString + ")'>Open</button></a><br /><br />";
                }
            }
            document.getElementById("responseCalanderInfo").innerHTML = calender;
        }
    }
    content.open("GET", "/calender", true);
    content.send();
}

function getAllCalenderEntries(){
    var output = "";
    content.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var obj = JSON.parse(this.responseText);
            if(obj == ""){
                output = "No Entires in database";
            }else {
                for( var i = 0; i < obj.length; i++){
                    var jsonString = JSON.stringify(obj[i]);
                    output += "<div style='margin-left: 2%;'>" + obj[i].date + "&nbsp;&nbsp;&nbsp;" + obj[i].name +
                        "<div style='float: right; margin-right: 1%;'><a><button class='btn btn-success' onclick='openCalenderEntry(" + jsonString + ")'>Open</button></a>&nbsp;&nbsp;<a><button class='btn btn-danger' onclick='deleteCalenderEntry(" + obj[i].id + ")'>Delete</button></a></div></div><br />";
                }
            }
            document.getElementById("calenderEntries").innerHTML = output;
        }
    }
    content.open("GET", "/calender/All", true);
    content.send();
}

function openCalenderEntry(jsonString){
    $('#CalenderEntry').modal('show');
    document.getElementById( "id").setAttribute('value', jsonString.id);
    document.getElementById("date").setAttribute('value', jsonString.date);
    document.getElementById("name").setAttribute('value', jsonString.name);
    document.getElementById("comment").setAttribute('value', jsonString.comment);
}

function saveChange(){
    var id = document.getElementById("id").value;
    var date = document.getElementById("date").value;
    var name = document.getElementById("name").value;
    var comment = document.getElementById("comment").value;

    var data = JSON.stringify({"id": id, "date": date, "name": name, "comment": comment});

    content.open("PUT", "/calender", true);
    content.setRequestHeader("Content-Type", "application/json");

    content.onreadystatechange = function () {
        if(this.status == 409){
            alert(this.responseText);
        }
        if (this.readyState == 4 && this.status == 202) {
            alert(this.responseText)
        }
    }
    content.send(data);

}

function createCalenderEntry(){
    var data = JSON.stringify({"date": document.getElementById("newEntryDate").value, "name": document.getElementById("newEntryName").value, "comment": document.getElementById("newEntryComment").value});

    content.open("POST", "/calender", false);
    content.setRequestHeader("Content-Type", "application/json");

    content.onreadystatechange = function () {
        if(this.status == 409){
            alert(this.responseText);
        }
        if (this.readyState == 4 && this.status == 201) {
            alert(this.responseText);
            $('#createCalenderEntiries').modal('hide');
        }
    }
    content.send(data);
    getCalanderEntryForCurrentDay();
}

function deleteCalenderEntry(id){
    content.open("DELETE", "/calender/" + id, true);
    content.onreadystatechange = function (){
        if(this.status == 200){
            alert(this.responseText);
        }else{
            alert(this.responseText);
        }
    }
   console.log(id);
    content.send();
    getAllCalenderEntries();
}


