# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://CANopenNode/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "gitsm://git@github.com/CANopenNode/CANopenLinux.git;protocol=ssh;branch=master"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "47044b10f3150ef9aa85b7b225c259d6be5e721c"

S = "${WORKDIR}/git"

# NOTE: this is a Makefile-only piece of software, so we cannot generate much of the
# recipe automatically - you will need to examine the Makefile yourself and ensure
# that the appropriate arguments are passed in.

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile_prepend() {
    cd ${S}
    git submodule init
    git submodule update
}

do_compile () {
    cd ${S}
    make
    cd ${S}/cocomm
    make
}

do_install () {
	# This is a guess; additional arguments may be required
    install -d ${D}${bindir}
    install -m 0755 ${S}/cocomm/cocomm ${D}${bindir}
    install -m 0755 ${S}/canopend ${D}${bindir}
}

FILES_${PN} += "${bindir}/"
