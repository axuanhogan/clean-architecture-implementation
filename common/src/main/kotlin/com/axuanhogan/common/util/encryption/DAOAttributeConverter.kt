package com.axuanhogan.common.util.encryption

import io.quarkus.logging.Log
import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.AttributeConverter
import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@ApplicationScoped
class DAOAttributeConverter(
    @param:ConfigProperty(name = "application.dao.encryption.enabled") private val enabled: Boolean,
    @param:ConfigProperty(name = "application.dao.aes.key") private val key: String,
    @param:ConfigProperty(name = "application.dao.aes.iv") private val iv: String,
): AttributeConverter<String, String> {

    private val algorithm = "AES/CBC/PKCS5Padding"
    private lateinit var keySpec: SecretKeySpec
    private lateinit var ivParamSpec: IvParameterSpec

    @PostConstruct
    fun initialize() {
        if (enabled) {
            this.keySpec = SecretKeySpec(key.toByteArray(), "AES")
            this.ivParamSpec = IvParameterSpec(iv.toByteArray())
            Log.info("Initialize DAOAttributeEncryptor done")
        } else {
            Log.info("DAOAttributeEncryptor is not initialized")
        }
    }

    override fun convertToDatabaseColumn(attributeValue: String?): String? {
        return if (enabled) {
            attributeValue?.let {
                AESUtil.encrypt(
                    algorithm = algorithm,
                    key = keySpec,
                    iv = ivParamSpec,
                    input = it,
                )
            }
        } else {
            attributeValue
        }
    }

    override fun convertToEntityAttribute(columnValue: String?): String? {
        return if (enabled) {
            columnValue?.let {
                AESUtil.decrypt(
                    algorithm = algorithm,
                    key = keySpec,
                    iv = ivParamSpec,
                    input = it,
                )
            }
        } else {
            columnValue
        }
    }
}
