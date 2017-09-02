SUMMARY = "A sample Python project"
HOMEPAGE = "https://github.com/petpaulsen/formenheizung"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

CONTROLLER_SERVICE_FILENAME = "formenheizung-control.service"
WEBUI_SERVICE_FILENAME = "formenheizung-webui.service"

SRC_URI = "git://github.com/petpaulsen/formenheizung.git;protocol=https;branch=feature/yocto \
           file://${CONTROLLER_SERVICE_FILENAME} \
           file://${WEBUI_SERVICE_FILENAME}"
FILES_${PN} += "${systemd_unitdir}/system/${CONTROLLER_SERVICE_FILENAME} \
                ${systemd_unitdir}/system/${WEBUI_SERVICE_FILENAME}"

# Modify these as desired
PV = "0.1.0+git${SRCPV}"
SRCREV = "feature/yocto"

S = "${WORKDIR}/git"

inherit setuptools3
inherit systemd

#SYSTEMD_SERVICE_${PN} = "hausmatik-example.service"
#FILES_${PN} += "${systemd_unitdir}/system/hausmatik-example.service"

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "test dev"
#PACKAGECONFIG[test] = ",,,python-coverage"
#PACKAGECONFIG[dev] = ",,,python-check-manifest"

do_install_append() {
	# install systemd service files
	install -d ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/${CONTROLLER_SERVICE_FILENAME} ${WORKDIR}/${WEBUI_SERVICE_FILENAME} ${D}/${systemd_unitdir}/system
}
