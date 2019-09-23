package com.armhansa.mobilephonebuyerguide.supporttest

import com.armhansa.mobilephonebuyerguide.entity.PhoneEntity
import com.armhansa.mobilephonebuyerguide.model.PhoneModel

class SupportModel {
    companion object {
        fun getSupportPhonesModel(): ArrayList<PhoneModel> {
            return arrayListOf(
                PhoneModel(
                    1, "Moto E4 Plus", "Motorola",
                    "https://cdn.mos.cms.futurecdn.net/grwJkAGWQp4EPpWA3ys3YC-650-80.jpg",
                    "First place in our list goes to the excellent Moto E4 Plus.",
                    179.99f, 4.9f, true
                ),
                PhoneModel(
                    2, "Nokia 6", "Nokia",
                    "https://cdn.mos.cms.futurecdn.net/8LWUERoxMAWavvVAAbxuac-650-80.jpg",
                    "Nokia is back in the mobile phone game and after a small price drop to the Nokia 6",
                    199.99f, 4.6f, false
                ),
                PhoneModel(
                    3, "Moto G4 Plus", "Motorola",
                    "https://cdn.mos.cms.futurecdn.net/52JJgbgWiGftNHV5UmMDfS-650-80.jpg",
                    "The spec for the G4 Plus is much the same as it was on the Moto G4",
                    179f, 4.7f, false
                ),
                PhoneModel(
                    4, "Moto G5", "Motorola",
                    "https://cdn.mos.cms.futurecdn.net/DcUtY6YfhoajHnoKUgGFqn-650-80.jpg",
                    "Motorola's Moto G5, a former best cheap phone in the world",
                    165f, 3.8f, true
                ),
                PhoneModel(
                    5, "Sony Xperia L1", "Sony",
                    "https://cdn.mos.cms.futurecdn.net/7dUFmtHkmH7La9dFzew7Ri-650-80.jpg",
                    "Currently the only Sony handset to take a position in our best cheap phone list",
                    171.99f, 4.3f, false
                )
            )
        }

        fun getSupportPhonesEntity(): ArrayList<PhoneEntity> {
            return arrayListOf(
                PhoneEntity(
                    1, "Moto E4 Plus", "Motorola",
                    "https://cdn.mos.cms.futurecdn.net/grwJkAGWQp4EPpWA3ys3YC-650-80.jpg",
                    "First place in our list goes to the excellent Moto E4 Plus.",
                    179.99f, 4.9f
                ),
                PhoneEntity(
                    2, "Nokia 6", "Nokia",
                    "https://cdn.mos.cms.futurecdn.net/8LWUERoxMAWavvVAAbxuac-650-80.jpg",
                    "Nokia is back in the mobile phone game and after a small price drop to the Nokia 6",
                    199.99f, 4.6f
                ),
                PhoneEntity(
                    3, "Moto G4 Plus", "Motorola",
                    "https://cdn.mos.cms.futurecdn.net/52JJgbgWiGftNHV5UmMDfS-650-80.jpg",
                    "The spec for the G4 Plus is much the same as it was on the Moto G4",
                    179f, 4.7f
                ),
                PhoneEntity(
                    4, "Moto G5", "Motorola",
                    "https://cdn.mos.cms.futurecdn.net/DcUtY6YfhoajHnoKUgGFqn-650-80.jpg",
                    "Motorola's Moto G5, a former best cheap phone in the world",
                    165f, 3.8f
                ),
                PhoneEntity(
                    5, "Sony Xperia L1", "Sony",
                    "https://cdn.mos.cms.futurecdn.net/7dUFmtHkmH7La9dFzew7Ri-650-80.jpg",
                    "Currently the only Sony handset to take a position in our best cheap phone list",
                    171.99f, 4.3f
                )
            )
        }
    }
}