using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;


namespace DepartmentDirect
{
    public partial class chatFaculty : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            // Any required server-side initialization
        }

        protected void SendMessage_Click(object sender, EventArgs e)
        {
            string userMessage = TextBox1.Text;

            // Code to handle message sending (e.g., WebSocket communication or database storage)
            // Example: SendMessageToServer(userMessage);

            // Clear the input field after sending the message
            TextBox1.Text = string.Empty;
        }

        // Additional methods to handle receiving messages, connecting to WebSocket, etc.
    }
}

