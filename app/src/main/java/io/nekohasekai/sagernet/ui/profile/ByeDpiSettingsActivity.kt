package io.nekohasekai.sagernet.ui.profile

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import io.nekohasekai.sagernet.Key
import io.nekohasekai.sagernet.R
import io.nekohasekai.sagernet.database.DataStore
import io.nekohasekai.sagernet.fmt.byedpi.ByeDpiBean

class ByeDpiSettingsActivity : ProfileSettingsActivity<ByeDpiBean>() {

    override fun createEntity() = ByeDpiBean()

    override fun ByeDpiBean.init() {
        DataStore.profileName = name
        DataStore.serverConfig = cmdArgs
    }

    override fun ByeDpiBean.serialize() {
        name = DataStore.profileName
        cmdArgs = DataStore.serverConfig
    }

    override fun PreferenceFragmentCompat.createPreferences(
        savedInstanceState: Bundle?,
        rootKey: String?,
    ) {
        addPreferencesFromResource(R.xml.byedpi_preferences)
        findPreference<EditTextPreference>(Key.SERVER_CONFIG)?.apply {
            setOnBindEditTextListener { editText ->
                editText.isSingleLine = false
            }
        }
    }
}
