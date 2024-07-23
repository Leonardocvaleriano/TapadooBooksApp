@file:JvmName("BookDetailsScreenRootKt")

package com.codeplace.tapadoobooksapp.presentation.screens.book_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSize4XL
import com.codeplace.tapadoobooksapp.presentation.ui.theme.SpaceSizeM



@Composable
fun BookDetailsScreenRoot(id:Int){
    BookDetailsScreen(id = id)
}

@Composable
fun BookDetailsScreen(
    id:Int,
    modifier:Modifier = Modifier

) {
    Column(modifier = modifier
        .padding(
            top = SpaceSize4XL,
            start = SpaceSizeM,
            end = SpaceSizeM,
            bottom = SpaceSize4XL)
    ) {

        Text(text = "Book Details Screen")
        Text(text = "Book ID: $id")
    }

}
@Composable
@Preview(name = "Light Mode", showBackground = true)
//@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Preview(name = "Full Preview", showSystemUi = true)
fun BookDetailsScreenPreview() {
    BookDetailsScreen(id = -1)
}
