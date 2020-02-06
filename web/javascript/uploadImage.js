/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        $("#profileImage").click(function(e) {
    $("#imageUpload").click();
  });

   function fasterPreview( uploader ) {
    if ( uploader.files && uploader.files[0] ){
          $('#profileImage').attr('src', window.URL.createObjectURL(uploader.files[0]) );
    }
}

$("#imageUpload").change(function(){
    fasterPreview( this );
});
