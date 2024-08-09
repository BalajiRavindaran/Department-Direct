using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DepartmentDirect
{
    public partial class publishNotification : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Any initialization code goes here
            }
        }

        protected void ButtonSubmit_Click(object sender, EventArgs e)
        {
            string notificationText = TextBoxNotification.Text;
            string selectedCategory = DropDownListCategories.SelectedValue;

            if (string.IsNullOrEmpty(notificationText) || selectedCategory == "Select")
            {
                // Handle validation errors
                // Show error message or use a validation control
                return;
            }

            // Code to handle the form submission
            // For example, sending data to an API or saving to a database

            // Example placeholder code:
            try
            {
                // Assume we have a method to save notification
                // SaveNotification(notificationText, selectedCategory);

                // Provide feedback to the user
                // Show a success message or redirect as needed
            }
            catch (Exception ex)
            {
                // Handle any exceptions
                // Show an error message
            }
        }
    }
}