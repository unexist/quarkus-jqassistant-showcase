/**
 * @package Quarkus-Arch-Testing-Showcase
 *
 * @file Unused util
 * @copyright 2020-2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.util.cycle.slice1;

import dev.unexist.showcase.todo.infrastructure.util.cycle.slice2.UnusedCycleB;

public class UnusedCycleA {
    private UnusedCycleB unusedCycleB;

    public UnusedCycleA() {
        this.unusedCycleB = new UnusedCycleB();
    }
}
