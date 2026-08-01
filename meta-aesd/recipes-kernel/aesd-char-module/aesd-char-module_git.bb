SUMMARY = "AESD character driver kernel module"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

SRC_URI = "git://github.com/cu-ecen-aeld/assignments-3-and-later-mickeyminthein.git;protocol=https;branch=main \
           file://aesdchar-start-stop \
          "

# Same commit you used for aesd-assignments
SRCREV = "61143aaf784402a25141fe690a3ef408da45e1b9"

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git/aesd-char-driver"

EXTRA_OEMAKE = "KERNELDIR=${STAGING_KERNEL_DIR}"

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/aesdchar-start-stop ${D}${sysconfdir}/init.d/aesdchar-start-stop
}

inherit update-rc.d
INITSCRIPT_NAME = "aesdchar-start-stop"
# Start before aesdsocket (lower number = earlier)
INITSCRIPT_PARAMS = "defaults 50"

FILES:${PN} += "${sysconfdir}/init.d/aesdchar-start-stop"

RPROVIDES:${PN} += "kernel-module-aesdchar"
