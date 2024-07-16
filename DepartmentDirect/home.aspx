<%@ Page Title="Home" Language="C#" MasterPageFile="~/DepartmentDirect.Master" AutoEventWireup="true" CodeBehind="home.aspx.cs" Inherits="DepartmentDirect.home" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <style>
        .container {
            width: 100%;
            max-width: 1100px; /* Adjust the max-width as needed */
            margin: 0 auto;
            padding: 20px;
            flex: 1; /* Make the container take up the available space */
        }

        .header {
            text-align: center;
            padding: 20px 0;
        }

            .header h1 {
                margin: 0;
                font-size: 65px;
                color: yellow;
            }

        .content {
            margin-top: 20px;
            font-size: 23px;
        }

            .content h2 {
                margin-top: 0;
                font-size: 40px;
                color: yellow;
            }

            .content p {
                margin: 10px 0;
            }

        .features {
            list-style: none;
            padding: 0;
        }

            .features li {
                margin: 10px 0;
            }

        strong {
            color: yellow;
        }
    </style>

    <div class="container">
        <div class="header">
            <h1>Welcome to DepartmentDirect</h1>
        </div>
        <div class="content">
            <p>
                DepartmentDirect is a comprehensive platform designed to streamline the interaction between students, faculty, and administrators in a university setting. Our application enables efficient communication, effective management of academic inquiries, and insightful data analytics to enhance the educational experience for everyone involved.
           
            </p>
            <h2>Key Features:</h2>
            <ul class="features">
                <li>
                    <strong>User Account Management:</strong> Easily create and manage accounts for students, faculty, and administrators. Our secure system ensures that your information is protected, and features like password reset and notification preferences provide a personalized user experience.
                </li>
                <li>
                    <strong>Question and Answer Interaction:</strong> Students can view all available departments and ask questions directly to faculty members. Past conversations are stored for easy reference, and our chat interface ensures that all interactions are smooth and efficient.
                </li>
                <li>
                    <strong>Faculty Management and Interaction:</strong> Faculty members can view and respond to all questions posed by students. They can also assign categories to questions, helping to organize information and make it easier to find relevant answers.
                </li>
                <li>
                    <strong>Admin Management and Analytics:</strong> Administrators have access to detailed analytics for each department, allowing them to monitor activity and performance. They can analyze data based on question categories, export data for further analysis, generate reports, and send notifications to all subscribed users.
                </li>
            </ul>
            <p>
                DepartmentDirect is designed to facilitate seamless communication within academic departments, making it easier for students to get the answers they need, for faculty to provide guidance, and for administrators to oversee and improve departmental functions.
           
            </p>
            <p>
                Join us today and experience a more connected and efficient academic environment with DepartmentDirect!
           
            </p>
        </div>
    </div>




</asp:Content>

