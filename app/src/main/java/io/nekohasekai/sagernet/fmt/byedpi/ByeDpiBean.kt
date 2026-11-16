package io.nekohasekai.sagernet.fmt.byedpi

import com.esotericsoftware.kryo.io.ByteBufferInput
import com.esotericsoftware.kryo.io.ByteBufferOutput
import io.nekohasekai.sagernet.fmt.AbstractBean
import io.nekohasekai.sagernet.fmt.KryoConverters

class ByeDpiBean : AbstractBean() {

    var cmdArgs: String = ""

    override fun initializeDefaultValues() {
        if (serverAddress.isNullOrEmpty()) serverAddress = "127.0.0.1"
        if (serverPort == null) serverPort = 1080
        super.initializeDefaultValues()
        if (cmdArgs.isEmpty()) {
            cmdArgs = "-s1 -o1 -Ar -o1 -At -f-1 -r1+s -As"
        }
    }

    override fun serialize(output: ByteBufferOutput) {
        output.writeInt(0)
        super.serialize(output)
        output.writeString(cmdArgs)
    }

    override fun deserialize(input: ByteBufferInput) {
        val version = input.readInt()
        super.deserialize(input)
        cmdArgs = input.readString()
    }

    override fun canMapping(): Boolean = false

    override fun applyFeatureSettings(other: AbstractBean) {
        if (other !is ByeDpiBean) return
        other.cmdArgs = cmdArgs
    }

    override fun clone(): ByeDpiBean {
        return KryoConverters.deserialize(ByeDpiBean(), KryoConverters.serialize(this))
    }

    companion object {
        @JvmField
        val CREATOR = object : CREATOR<ByeDpiBean>() {
            override fun newInstance() = ByeDpiBean()
            override fun newArray(size: Int) = arrayOfNulls<ByeDpiBean>(size)
        }
    }
}
