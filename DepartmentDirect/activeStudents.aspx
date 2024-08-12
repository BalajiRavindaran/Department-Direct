<%@ Page Title="" Language="C#" MasterPageFile="~/DepartmentDirect.Master" AutoEventWireup="true" CodeBehind="activeStudents.aspx.cs" Inherits="DepartmentDirect.activeStudents" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
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
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .table-container {
            width: 100%;
            max-width: 1200px;
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: #f9f9f9;
            color: black;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }

        th {
            background: #912338;
            color: white;
        }

        tr:nth-child(even) {
            background: #f2f2f2;
        }

        tr:hover {
            background: #ddd;
        }

        .link-button {
            color: #912338;
            text-decoration: none;
        }

        .link-button:hover {
            text-decoration: underline;
        }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="container">
        <h1 class="title">Active Students</h1>
        <asp:Label ID="NoStudentsLabel" runat="server" ForeColor="Red" Visible="false">
            No active students present
        </asp:Label>
        <div class="table-container">
            <asp:GridView ID="StudentGridView" runat="server" AutoGenerateColumns="False" CssClass="table" OnRowCommand="StudentGridView_RowCommand">
                <Columns>
                    <asp:BoundField DataField="StudentID" HeaderText="Student ID" />
                    <asp:BoundField DataField="Name" HeaderText="Name" />
                    <asp:TemplateField HeaderText="Actions">
                        <ItemTemplate>
                            <asp:LinkButton ID="StudentLinkButton" runat="server" CssClass="link-button"
                                CommandArgument='<%# Eval("StudentID") %>' CommandName="ViewDetails">
                                View Details
                            </asp:LinkButton>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
    </div>
</asp:Content>
