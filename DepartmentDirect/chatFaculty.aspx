<%@ Page Title="Chat with Faculty" Language="C#" MasterPageFile="~/DepartmentDirect.Master" AutoEventWireup="true" CodeBehind="chatFaculty.aspx.cs" Inherits="DepartmentDirect.chatFaculty" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <style>
        body {
            margin: 0;
            min-height: 100vh;
            display: grid;
            color: #ffffff;
        }

        h2 {
            font-size: 3.1vw;
            font-weight: normal;
        }

        h3, h4, h5, h6, label {
            font-size: 2.1vw;
            color: red;
        }

        .blink {
            animation: blinker 1.5s linear infinite;
        }

        @keyframes blinker {
            50% {
                opacity: 0;
            }
        }

        .title {
            padding: 20px 0 0 0;
        }

        .title {
            color: #912338;
            font-size: 50px;  
        }

        .chat-container {
            background: #ffffff;
            color: black;
            border-radius: 10px;
            padding: 20px;
            margin-top: 20px;
        }

        .chat-box {
            border: 1px solid #ccc;
            padding: 10px;
            height: 300px;
            overflow-y: scroll;
            background: #f9f9f9;
        }

        .message {
            margin-bottom: 10px;
        }

        .message.user {
            text-align: right;
        }

        .message.faculty {
            text-align: left;
        }

        .input-container {
            margin-top: 10px;
        }

        input[type="text"] {
            width: 80%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            padding: 10px;
            background-color: #912338;
            color: white;
            border: none;
            border-radius: 5px;
        }

        button:hover {
            background-color: #800020;
        }

    </style>

    <br />
    <div class="container">
        <div class="row">
            <div class="col-md-6 mx-auto">

                <div class="card">
                    <div class="card-body">

                        <div class="row">
                            <div class="col">
                                <center>
                                    <h1 class="title">Chat with Faculty</h1>
                                </center>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <hr />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <div class="chat-container">
                                    <div class="chat-box" id="chat-box">
                                        <!-- Messages will be displayed here -->
                                    </div>
                                    <div class="input-container">
                                        <asp:TextBox CssClass="form-control" ID="TextBox1" runat="server" placeholder="Type a message..." />
                                        <button onclick="sendMessage()">Send</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <br />
                <br />
                <a class="btn btn-primary" href="home.aspx"><i class="fas fa-angle-left"></i> <i class="fa fa-home"></i></a>
                <br />
                <br />

            </div>
        </div>
    </div>

    <script>
        const chatBox = document.getElementById('chat-box');

        function sendMessage() {
            const messageInput = document.getElementById('<%= TextBox1.ClientID %>');
            const message = messageInput.value;

            // Display the message in the chat box
            const messageElement = document.createElement('div');
            messageElement.className = 'message user';
            messageElement.textContent = message;
            chatBox.appendChild(messageElement);

            messageInput.value = '';

            // Send the message to the server (WebSocket implementation needed here)
            // Example: socket.send(JSON.stringify({ message }));
        }

        // Function to display received messages (to be called when a message is received from the server)
        function displayReceivedMessage(message) {
            const messageElement = document.createElement('div');
            messageElement.className = 'message faculty';
            messageElement.textContent = message;
            chatBox.appendChild(messageElement);
        }
    </script>

</asp:Content>
