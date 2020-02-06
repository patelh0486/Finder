

// $(document).ready(function(){
//  $("#MessageButton").click(function MessageAlert(e){
//    alert("Your message has been sent:)");
//  });
//});

function Confirmation(){ 
              
            var val =  alert("message has been sent"); 
              if(val == true){
              document.getElementById('Mid').value = "";
              document.getElementById('MessageTxt').value = "";
          } 
               
            } 
            
function ReplyClick(){
   // alert("button called");
   
      var x = document.getElementById("MessageBox");
    
      if (x.style.display = "none") {
      x.style.display = "block";
  } 
}
   
   
function myPopup1() {
  var popup = document.getElementById("myPopup");
  popup.classList.toggle("show");
}   