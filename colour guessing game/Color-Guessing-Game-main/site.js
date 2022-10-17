var square = document.querySelectorAll(".square");
var h1=document.querySelector("h1");
var resetButton = document.getElementById("reset");
var message= document.getElementById("message");
var displayedColor = document.getElementById("displayedColor");
var easyBtn = document.getElementById("easyBtn");
var hardBtn = document.getElementById("hardBtn");
var numSquare=6;
var colors=generateRandomColor(numSquare);
var pickedColor=colors[Math.floor(Math.random()*numSquare)];

easyBtn.addEventListener("click", function()
{
    h1.style.backgroundColor= null;
    message.textContent="";
    easyBtn.classList.add("selected");
    hardBtn.classList.remove("selected");
    numSquare=3;
    colors=generateRandomColor(numSquare);
    pickedColor=colors[Math.floor(Math.random()*numSquare)];
    displayedColor.textContent=pickedColor;

    for(var i=0; i<square.length; i++)
    {
        if(colors[i])
        {
            square[i].style.backgroundColor=colors[i];
        }
        else
        {
            square[i].style.backgroundColor="#232323";
        }

    }

});
hardBtn.addEventListener("click", function()
{
    message.textContent="";
    easyBtn.classList.remove("selected");
    hardBtn.classList.add("selected");
    numSquare=6;
    colors=generateRandomColor(numSquare);
    pickedColor=colors[Math.floor(Math.random()*numSquare)];
    displayedColor.textContent=pickedColor;
    h1.style.backgroundColor= null;

    for(var i=0; i<numSquare; i++)
    {
        square[i].style.backgroundColor=colors[i];
        square[i].style.di
    }
});


displayedColor.textContent=pickedColor;

resetButton.addEventListener("click", function()
{
    message.textContent="";
    colors=generateRandomColor(numSquare);
    pickedColor=colors[Math.floor(Math.random()*numSquare)];
    displayedColor.textContent=pickedColor;
    resetButton.textContent="NEW COLOR";
    h1.style.backgroundColor= null;

    for(var i=0; i<numSquare; i++)
    {
        square[i].style.backgroundColor =colors[i];
    }
    
});

for(var j=0; j<numSquare; j++)
   {
       square[j].style.backgroundColor =colors[j];
       square[j].addEventListener("click", function()
       {
           var clickedColor=this.style.backgroundColor;
           if(clickedColor===pickedColor)
           {
               message.textContent="Correct!";
               resetButton.textContent="Play Again?";
               h1.style.backgroundColor=clickedColor;
               for(var p=0; p<numSquare; p++)
               {
                   square[p].style.backgroundColor=clickedColor;
               }
           }
           else
           {
               this.style.backgroundColor="#232323";
               message.textContent="Try Again";
           }
       })
   }
function generateRandomColor(num)
{
    var c=[];
    for(var i=0; i<num; i++)
    {
        
        var temp1=Math.floor(Math.random()*256);
        var temp2=Math.floor(Math.random()*256);
        var temp3=Math.floor(Math.random()*256);
        var temp="rgb("+temp1+", "+temp2+", "+temp3+")";
        
        
        if(temp1==35 && temp2==35 && temp3==35)     //Generated Color will be different from background color
        {
            i--;
            continue;
        }
        c.push(temp);
    }
    return c;

}






