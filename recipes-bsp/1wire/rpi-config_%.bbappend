ENABLE_1WIRE = "${@bb.utils.contains('MACHINE_FEATURES', '1wire', '1', '0', d)}"

do_deploy_append() {
    # 1-wire support
    if [ "${ENABLE_1WIRE}" = "1" ]; then
        echo "# Enable 1-wire support" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
        echo "dtoverlay=w1-gpio,gpiopin=4" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
    fi
}
