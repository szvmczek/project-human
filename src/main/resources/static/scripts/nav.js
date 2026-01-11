(() => {
    const mobileNav = document.querySelector(".nav__mobile");
    if (!mobileNav) return;

    const burger = mobileNav.querySelector(".burger");
    const menu = mobileNav.querySelector(".menu");
    if (!burger || !menu) return;

    const focusableSelector = 'a[href], button:not([disabled]), [tabindex]:not([tabindex="-1"])';

    function setOpen(isOpen) {
        mobileNav.classList.toggle("is-open", isOpen);
        burger.setAttribute("aria-expanded", String(isOpen));
        menu.setAttribute("aria-hidden", String(!isOpen));

        if (isOpen) {
            const firstLink = menu.querySelector("a");
            firstLink?.focus({ preventScroll: true });
        } else {
            burger.focus({ preventScroll: true });
        }
    }

    function isOpen() {
        return mobileNav.classList.contains("is-open");
    }

    burger.addEventListener("click", (e) => {
        e.stopPropagation();
        setOpen(!isOpen());
    });

    document.addEventListener("click", (e) => {
        if (!isOpen()) return;
        const target = e.target;
        if (!(target instanceof Node)) return;

        if (!mobileNav.contains(target)) {
            setOpen(false);
        }
    });

    document.addEventListener("keydown", (e) => {
        if (!isOpen()) return;

        if (e.key === "Escape") {
            e.preventDefault();
            setOpen(false);
            return;
        }

        if (e.key === "Tab") {
            const focusables = Array.from(menu.querySelectorAll(focusableSelector));
            if (!focusables.length) return;

            const first = focusables[0];
            const last = focusables[focusables.length - 1];
            const active = document.activeElement;

            if (e.shiftKey && active === first) {
                e.preventDefault();
                last.focus();
            } else if (!e.shiftKey && active === last) {
                e.preventDefault();
                first.focus();
            }
        }
    });

    menu.addEventListener("click", (e) => {
        const target = e.target;
        if (target instanceof HTMLElement && target.closest("a")) {
            setOpen(false);
        }
    });

    window.addEventListener("resize", () => {
        if (window.matchMedia("(min-width: 621px)").matches && isOpen()) {
            setOpen(false);
        }
    });
})();
