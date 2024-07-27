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

        .title {
            color: #912338;
            font-size: 50px;  
            text-align: center;
            padding: 20px 0;
        }

        .container {
            display: flex;
            height: 80vh;
        }

        .conversation-list {
            width: 30%;
            background: #f1f1f1;
            color: #000;
            border-right: 1px solid #ccc;
            overflow-y: scroll;
        }

        .conversation-item {
            padding: 15px;
            cursor: pointer;
            border-bottom: 1px solid #ccc;
        }

        .conversation-item:hover {
            background: #e9e9e9;
        }

        .chat-section {
            width: 70%;
            padding: 20px;
            display: flex;
            flex-direction: column;
        }

        .chat-box {
            flex: 1;
            border: 1px solid #ccc;
            padding: 10px;
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
            display: flex;
        }

        input[type="text"] {
            flex: 1;
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
            margin-left: 10px;
        }

        button:hover {
            background-color: #800020;
        }
    </style>

    <div class="title">Chat with Faculty</div>

    <div class="container">
        <div class="conversation-list" id="conversation-list">
            <!-- Conversations will be listed here -->
            <div class="conversation-item" onclick="loadConversation(1)">Conversation 1</div>
            <div class="conversation-item" onclick="loadConversation(2)">Conversation 2</div>
            <!-- More conversation items as needed -->
        </div>

        <div class="chat-section">
            <div class="chat-box" id="chat-box">
                <!-- Messages will be displayed here -->
            </div>
            <div class="input-container">
                <asp:TextBox CssClass="form-control" ID="TextBox1" runat="server" placeholder="Type a message..." />
                <button onclick="sendMessage()">Send</button>
            </div>
        </div>
    </div>

    <br />
    <div style="text-align:center;">
        <a class="btn btn-primary" href="home.aspx"><i class="fas fa-angle-left"></i> <i class="fa fa-home"></i></a>
    </div>
    <br />

    <script>
        const chatBox = document.getElementById('chat-box');
        const conversationList = document.getElementById('conversation-list');

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

        function displayReceivedMessage(message) {
            const messageElement = document.createElement('div');
            messageElement.className = 'message faculty';
            messageElement.textContent = message;
            chatBox.appendChild(messageElement);
        }

        function loadConversation(conversationId) {
            // Clear the chat box
            chatBox.innerHTML = '';

            // Load messages for the selected conversation (fetch from server/database)
            // Example:
            // fetch(`/api/conversations/${conversationId}`)
            //   .then(response => response.json())
            //   .then(messages => {
            //     messages.forEach(message => displayReceivedMessage(message));
            //   });
        }
    </script>

</asp:Content>
