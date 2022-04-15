var spaceInfomationRequest = new XMLHttpRequest();

function getAllSpaceInformation(){
    var spaceInfos = "";
    spaceInfomationRequest.open("GET", "/SpaceInformation/All", false);
    spaceInfomationRequest.send();
    spaceInfomationRequest.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200){
           var spaceInfoJSON = JSON.parse(this.responseText);
           console.log(spaceInfoJSON);
           if(spaceInfoJSON === ""){
               spaceInfos = "No Entries in database!";
           }
           for(var i = 0; i < spaceInfoJSON.length; i++){
               var spaceInfoObject = JSON.stringify(spaceInfoJSON[i]);
               spaceInfos += "Title: " + spaceInfoJSON[i].title + " <button type='button' class='btn btn-success' " +
                   "onclick='openSpaceInformation(" + spaceInfoObject + ")'>Open</button>"
           }
           document.getElementById("allSpaceInformations").innerHTML = spaceInfoObject;
        }
    }
}

function openSpaceInformation(siInfo){
    $('#SIDetials').modal('show');
    document.getElementById("spaceInformationID").setAttribute('value',siInfo.uuid);
    document.getElementById("spaceInformationTitle").setAttribute('value', siInfo.title);
    document.getElementById("spaceInformationText").setAttribute('value', siInfo.info);
    document.getElementById("spaceInformationCreationDate").setAttribute('value', siInfo.creationDate);
    document.getElementById("spaceInformationModificationDate").setAttribute('value', siInfo.modificationDate);
}