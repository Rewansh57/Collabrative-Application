import { Client, Message } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

document.addEventListener("DOMContentLoaded", () => {
  const response = document.getElementById("response") as HTMLElement | null;
   const message=document.getElementById("message") as HTMLInputElement
   const username=document.getElementById("username") as HTMLInputElement
   const sendBtn=document.getElementById("send-button") as HTMLElement


  const client = new Client({
    webSocketFactory: () => new SockJS('/ws'),
    reconnectDelay: 5000,
    debug: (str) => console.log(str),
  });

 

  client.onConnect = () => {
    console.log("Connected broker");

    client.subscribe('/topic/message', (message: Message) => {
      const msg = JSON.parse(message.body);
      if (msg && response) {
        response.textContent = msg;
      }
    });
  };
  client.activate();


  sendBtn?.addEventListener('click',()=>{
   const  content=message?.value.trim;
   const name=username?.value.trim;


  const text={
    username:name,
    messageBody:content
  }

  client.publish({
    destination:"/app/public-chat"
    ,
    body:JSON.stringify(text)
  }
    
  )


   



  })  
client.onStompError = (frame) => {
    console.error('Eror', frame);
  };
if (message){
    message.value =""
    
}
else {
    alert("Enter both username and body to send")

}
 })

