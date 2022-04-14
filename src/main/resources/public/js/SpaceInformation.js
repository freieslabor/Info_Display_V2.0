var spaceInfomationRequest = new XMLHttpRequest();

function getAllSapceInformation(){
    var spaceInfos = "";
    content.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
           var spaceInfoJSON = JSON.parse(this.responseText);
           if(spaceInfoJSON === ""){
               spaceInfos = "No Entries in database!";
           }
           for(var i = 0; i < spaceInfoJSON.length; i++){
               var spaceInfoObject = JSON.stringify(spaceInfoJSON[i]);
               spaceInfos += "Title: " + spaceInfoJSON[i].title + " <button type='button' class='btn btn-success' " +
                   "onclick='openSpaceInformation(" + spaceInfoObject + ")'>Open</button>"
           }
        }
    }
    content.open("GET", "/SpaceInfomation/All", true);
    content.send();
}

function openSpaceInformation(object){
    $('#SISDetials').modal('show');
    document.getElementById("spaceInformationID").setAttribute('value',object.uuid);
    document.getElementById("spaceInformationTitle").setAttribute('value', object.title);
    document.getElementById("spaceInformationText").setAttribute('value', object.info);

}